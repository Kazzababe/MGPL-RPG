package com.minigamepalooza.src.utils;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BlockIterator;

public class Utils {
	public static LivingEntity getTarget(final Player player) {
		BlockIterator iterator = new BlockIterator(player.getWorld(), player.getLocation().toVector(), player.getEyeLocation().getDirection(), 0, 100);
		LivingEntity target = null;
		while(iterator.hasNext()) {
			Block item = iterator.next();
			for(Entity entity : player.getNearbyEntities(100, 100, 100)) {
				if(entity instanceof LivingEntity == false || entity instanceof Player) continue;
				int acc = 2;
				for(int x = -acc; x < acc; x++) {
					for(int z = -acc; z < acc; z++) {
						for(int y = -acc; y < acc; y++) {
							if(entity.getLocation().getBlock().getRelative(x, y, z).equals(item) && !entity.isDead()) {
								return target = (LivingEntity) entity;
							}
						}
					}
				}
			}
		}
		return target;
	}
	
	public static LivingEntity getPlayerTarget(final Player player) {
		BlockIterator iterator = new BlockIterator(player.getWorld(), player.getLocation().toVector(), player.getEyeLocation().getDirection(), 0, 100);
		LivingEntity target = null;
		while(iterator.hasNext()) {
			Block item = iterator.next();
			for(Entity entity : player.getNearbyEntities(100, 100, 100)) {
				if(entity instanceof Player == false) continue;
				if(((Player) entity).equals(player)) continue;
				int acc = 2;
				for(int x = -acc; x < acc; x++) {
					for(int z = -acc; z < acc; z++) {
						for(int y = -acc; y < acc; y++) {
							if(entity.getLocation().getBlock().getRelative(x, y, z).equals(item) && !entity.isDead()) {
								return target = (LivingEntity) entity;
							}
						}
					}
				}
			}
		}
		return target;
	}
}
