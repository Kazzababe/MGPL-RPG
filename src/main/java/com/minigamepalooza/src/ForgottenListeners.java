package com.minigamepalooza.src;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ForgottenListeners implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Forgotten.addRPGPlayer(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Forgotten.removeRPGPlayer(event.getPlayer());
		event.setQuitMessage(null);
	}
}
