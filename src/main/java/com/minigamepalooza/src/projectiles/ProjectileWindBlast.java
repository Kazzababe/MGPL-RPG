package com.minigamepalooza.src.projectiles;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.minigamepalooza.src.Forgotten;

public class ProjectileWindBlast extends ProjectileItem {
	private static final int LIFE_SPAN = 5;
	
	public ProjectileWindBlast(Player player) {
		super(player, new ItemStack(Material.GOLD_BLOCK, 1), LIFE_SPAN * 20, 0.85F);
	}

	@Override
	public void onHitBlock(Location location) {
		
	}

	@Override
	public void onHitEntity(LivingEntity target) {
		target.setNoDamageTicks(0);
		target.damage(2, Forgotten.getPlayer(this.getOwnerUniqueId()).getPlayer());
	}
}
