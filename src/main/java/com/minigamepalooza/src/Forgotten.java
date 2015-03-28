package com.minigamepalooza.src;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.minigamepalooza.base.PaloozaBase;
import com.minigamepalooza.base.sql.SQL;
import com.minigamepalooza.core.GameSettings;
import com.minigamepalooza.src.listeners.DamageListeners;
import com.minigamepalooza.src.listeners.PlayerInteractListeners;
import com.minigamepalooza.src.players.RPGPlayer;

public class Forgotten extends PaloozaBase {
	public static final String DB_NAME = "Forgotten";
	
	private final static Map<UUID, RPGPlayer> players = new HashMap<UUID, RPGPlayer>();

	@Override
	public void onPaloozaEnable() {
		GameSettings.respawn = true;
        GameSettings.pvp = true;
        GameSettings.hunger = true;
        GameSettings.maxPlayers = 100;
        GameSettings.minPlayers = 1;
        GameSettings.friendlyMobs = true;
        GameSettings.hostileMobs = true;
        
        this.registerListener(new PlayerInteractListeners());
        this.registerListener(new ForgottenListeners());
        this.registerListener(new DamageListeners());
	}

	@Override
	public SQL getNewSQL() throws SQLException {
		return new com.minigamepalooza.src.sql.SQL(this);
	}
	
	public static void addRPGPlayer(Player player) {
		players.put(player.getUniqueId(), new RPGPlayer(player));
	}
	
	public static void removeRPGPlayer(Player player) {
		players.remove(player.getUniqueId());
	}
	
	public static RPGPlayer getRPGPlayer(Player player) {
		return getRPGPlayer(player.getUniqueId());
	}
	
	public static RPGPlayer getRPGPlayer(UUID uuid) {
		return players.get(uuid);
	}
}
