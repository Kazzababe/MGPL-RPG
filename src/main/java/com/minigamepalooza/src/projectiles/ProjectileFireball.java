package com.minigamepalooza.src.projectiles;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minigamepalooza.src.Forgotten;

public class ProjectileFireball extends ProjectileItem {
	private static final int LIFE_SPAN = 10;
	
	public ProjectileFireball(Player player) {
		super(player, new ItemStack(Material.FIREBALL, 1), LIFE_SPAN * 20, 0.9F);
	}

	@Override
	public void onHitBlock(Location location) {
		location.getWorld().playEffect(location, Effect.EXPLOSION_LARGE, 0);
	}

	@Override
	public void onHitEntity(LivingEntity target) {
		target.damage(5, Forgotten.getRPGPlayer(this.getOwnerUniqueId()).getPlayer());
		if(Math.random() < 0.25) {
			target.setFireTicks(60);
		}
	}
}
