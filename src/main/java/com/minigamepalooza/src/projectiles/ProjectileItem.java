package com.minigamepalooza.src.projectiles;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.stirante.MoreProjectiles.projectile.ItemProjectile;

public abstract class ProjectileItem extends Projectile {
	private ItemProjectile projectile;
	
	public ProjectileItem(Player player, ItemStack item, int lifeSpan, float power) {
		super(player.getUniqueId(), lifeSpan);
		
		this.projectile = new ItemProjectile(this.getNormalName(), player, item, power);
	}
	
	public ProjectileItem(Player player, Location location, ItemStack item, int lifeSpan, float power) {
		super(player.getUniqueId(), lifeSpan);
		
		this.projectile = new ItemProjectile(this.getNormalName(), location, item, player, power);
	}

	@Override
	public void dispose() {
		this.projectile.die();
	}
}
