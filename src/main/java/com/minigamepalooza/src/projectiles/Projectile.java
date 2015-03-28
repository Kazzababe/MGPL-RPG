package com.minigamepalooza.src.projectiles;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import com.minigamepalooza.src.Forgotten;
import com.stirante.MoreProjectiles.event.BlockProjectileHitEvent;
import com.stirante.MoreProjectiles.event.ItemProjectileHitEvent;

public abstract class Projectile extends BukkitRunnable implements Listener {
	private UUID uuid;
	
	private int life;
	private int lifeSpan;
	
	public Projectile(UUID uuid, int lifeSpan) {
		this.uuid = uuid;
		this.lifeSpan = lifeSpan;
		
		Forgotten.getPlugin().registerListener(this);
		
		this.runTaskTimer(Forgotten.getPlugin(), 1, 1);
	}
	
	public UUID getOwnerUniqueId() {
		return this.uuid;
	}
	
	public int getLife() {
		return this.life;
	}
	
	public int getLifeSpan() {
		return this.lifeSpan;
	}
	
	public abstract void dispose();
	public abstract void onHitBlock(Location location);
	public abstract void onHitEntity(LivingEntity target);
	
	private void deregister() {
		ItemProjectileHitEvent.getHandlerList().unregister(this);
		BlockProjectileHitEvent.getHandlerList().unregister(this);
		
		this.cancel();
	}
	
	@EventHandler
	public void onBlockHit(BlockProjectileHitEvent event) {
		if(event.getProjectile().getProjectileName().equals(this.getNormalName())) {
			if(event.getHitEntity() != null) {
				this.onHitEntity(event.getHitEntity());
			} else if(event.getHitBlock() != null) {
				this.onHitBlock(event.getHitBlock().getLocation());
			}
			this.deregister();
		}
	}
	
	@EventHandler
	public void onItemHit(ItemProjectileHitEvent event) {
		System.out.println("GET SHOT BITCH");
		if(event.getProjectile().getProjectileName().equals(this.getNormalName())) {
			if(event.getHitEntity() != null) {
				this.onHitEntity(event.getHitEntity());
			} else if(event.getHitBlock() != null) {
				this.onHitBlock(event.getHitBlock().getLocation());
			}
			this.deregister();
		}
	}

	@Override
	public void run() {
		this.life++;
		if(this.life >= this.lifeSpan) {
			this.dispose();
			this.deregister();
		}
	}
	
	protected String getNormalName() {
		return this.uuid.toString().replaceAll("[-+.^:,]","");
	}
}
