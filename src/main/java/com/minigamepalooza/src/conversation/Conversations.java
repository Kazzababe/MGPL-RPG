package com.minigamepalooza.src.conversation;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class Conversations {
	private static final Map<String, Conversation> CURRENT_CONVERSATION = new HashMap<String, Conversation>();
	
	public static void startConversation(Player player, Conversation conversation) {
		CURRENT_CONVERSATION.put(player.getName(), conversation.clone());
	}
	
	public static boolean inConversation(Player player) {
		return CURRENT_CONVERSATION.containsKey(player.getName());
	}
	
	public static Conversation getConversation(Player player) {
		return CURRENT_CONVERSATION.get(player.getName());
	}
	
	public static void endConversation(Player player) {
		CURRENT_CONVERSATION.remove(player.getName());
	}
}
