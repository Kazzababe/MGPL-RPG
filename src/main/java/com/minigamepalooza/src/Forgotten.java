package com.minigamepalooza.src;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.minigamepalooza.base.PaloozaBase;
import com.minigamepalooza.base.sql.SQL;
import com.minigamepalooza.core.GameSettings;
import com.minigamepalooza.src.conversation.Conversation;
import com.minigamepalooza.src.conversation.Conversations;
import com.minigamepalooza.src.conversation.dialogue.Dialogue;
import com.minigamepalooza.src.conversation.dialogue.DialogueAnswer;
import com.minigamepalooza.src.conversation.dialogue.DialogueMessage;
import com.minigamepalooza.src.conversation.dialogue.DialogueQuestion;
import com.minigamepalooza.src.listeners.DamageListeners;
import com.minigamepalooza.src.listeners.PlayerInteractListeners;
import com.minigamepalooza.src.listeners.PlayerListeners;
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
        this.registerListener(new PlayerListeners());
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
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			String commandName = command.getName();
			
			if (commandName.equalsIgnoreCase("respond")) {
				long time = Long.parseLong(args[0]);
				String message = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
				
				if (Conversations.inConversation(player)) {
					Conversation conversation = Conversations.getConversation(player);
					if (conversation.getStartTime() == time) {
						Dialogue currentDialogue = conversation.getDialogue();
						if (currentDialogue instanceof DialogueQuestion) {
							DialogueQuestion dialogue = (DialogueQuestion) currentDialogue;
							for (DialogueAnswer answer : dialogue.getAnswers()) {
								if (answer.getMessage().equals(message)) {
									answer.getCallback().onClick(conversation, player);
									break;
								}
							}
						}
					}
				}
			} else if (commandName.equalsIgnoreCase("next")) {
				long time = Long.parseLong(args[0]);
				
				if (Conversations.inConversation(player)) {
					Conversation conversation = Conversations.getConversation(player);
					if (conversation.getStartTime() == time) {
						Dialogue currentDialogue = conversation.getDialogue();
						if (currentDialogue instanceof DialogueMessage) {
							conversation.next(player);
						}
					}
				}
			}
		}
		return true;
	}
}
