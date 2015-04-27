package com.minigamepalooza.src.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.minigamepalooza.base.utils.Bar;
import com.minigamepalooza.src.Forgotten;
import com.minigamepalooza.src.players.PlayerStats;
import com.minigamepalooza.src.players.RPGPlayer;

public class DamageListeners implements Listener {
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		LivingEntity entity = event.getEntity();
		if (entity.getKiller() != null) {
			RPGPlayer player = Forgotten.getRPGPlayer(entity.getKiller());
			PlayerStats stats = player.getStats();

			stats.incrementExperience((int) entity.getMaxHealth());
			player.checkExperience();
		}
	}

	@EventHandler (priority = EventPriority.MONITOR)
	public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if (damager instanceof Player) {
			Entity damaged = event.getEntity();
			if (damaged instanceof Player == false && damaged instanceof LivingEntity) {
				final Player damagerPlayer = (Player) damager;
				LivingEntity damagedEntity = (LivingEntity) damaged;
				
				Bar.setBossBar(damagerPlayer, damagedEntity.getType().getName(), 100);
			}
		}
	}
}
