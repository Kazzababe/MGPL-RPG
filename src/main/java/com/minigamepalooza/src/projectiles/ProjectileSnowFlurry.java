package com.minigamepalooza.src.projectiles;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minigamepalooza.src.Forgotten;

public class ProjectileSnowFlurry extends ProjectileItem {
	private static final int LIFE_SPAN = 1;
	
	public ProjectileSnowFlurry(Player player, Location loc) {
		super(player, loc, new ItemStack(Material.SNOW_BALL, 1), LIFE_SPAN * 20, 0.5F);
	}

	@Override
	public void onHitBlock(Location location) {
		
	}

	@Override
	public void onHitEntity(LivingEntity target) {
		target.setNoDamageTicks(0);
		target.damage(1, Forgotten.getPlayer(this.getOwnerUniqueId()).getPlayer());
	}
}
