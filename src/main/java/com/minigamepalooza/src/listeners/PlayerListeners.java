package com.minigamepalooza.src.listeners;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.minigamepalooza.src.Forgotten;

public class PlayerListeners implements Listener {
	

	public PlayerListeners() {
		
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		new BukkitRunnable() {
        	public void run() {
		        NPCRegistry registry = CitizensAPI.getNPCRegistry();
		        NPC npc = registry.createNPC(EntityType.PLAYER, "Wizard Master");
		        npc.spawn(player.getLocation().add(0, 1, 0));
        	}
        }.runTaskLater(Forgotten.getPlugin(), 20L * 2);
	}
}
