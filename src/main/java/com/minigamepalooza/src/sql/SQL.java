package com.minigamepalooza.src.sql;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import com.minigamepalooza.base.PaloozaBase;
import com.minigamepalooza.src.Forgotten;

public class SQL extends com.minigamepalooza.base.sql.SQL {

	public SQL(PaloozaBase plugin) throws SQLException {
		super(plugin);
	}

	@Override
	public void updatePlayers() {
		if(!this.isConnected()) {
            return;
        }
		int lastPlayerCount = Bukkit.getOnlinePlayers().size();
		new AsyncRunnable() {
			@Override
			public void run() {
				try(PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM `palooza`.`games` WHERE `ip` = ? AND `port` = ? AND `game` = ?")) {
					statement.setString(1, InetAddress.getLocalHost().getHostAddress());
					statement.setInt(2, Bukkit.getPort());
					statement.setString(3, Forgotten.DB_NAME);
					ResultSet resultSet = statement.executeQuery();
					if(!resultSet.first()) {
						plugin.getLogger().info("Closing due to server being removed from database");
						Bukkit.shutdown();
						return;
					}
				} catch (SQLException | UnknownHostException e) {
					e.printStackTrace();
				}
				try(PreparedStatement statement = getConnection().prepareStatement("UPDATE `palooza`.`games` SET `players` = ?, `last_updated` = ? WHERE `ip` = ? AND `port` = ?")) {
					statement.setInt(1, lastPlayerCount);
					statement.setLong(2, System.currentTimeMillis());
					statement.setString(3, InetAddress.getLocalHost().getHostAddress());
					statement.setInt(4, Bukkit.getPort());
					statement.executeUpdate();
				} catch(SQLException | UnknownHostException e) {
					e.printStackTrace();
				}
			}
		};
	}
}
