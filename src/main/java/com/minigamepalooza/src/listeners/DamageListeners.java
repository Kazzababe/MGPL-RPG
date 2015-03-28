package com.minigamepalooza.src.listeners;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.minigamepalooza.src.Forgotten;
import com.minigamepalooza.src.players.PlayerStats;
import com.minigamepalooza.src.players.RPGPlayer;

public class DamageListeners implements Listener {
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		LivingEntity entity = event.getEntity();
		if(entity.getKiller() != null) {
			RPGPlayer player = Forgotten.getRPGPlayer(entity.getKiller());
			PlayerStats stats = player.getStats();
			
			stats.incrementExperience((int) entity.getMaxHealth());
			player.checkExperience();
		}
	}
}
