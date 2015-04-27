package com.minigamepalooza.src.conversation.dialogue;

import org.bukkit.entity.Player;

import com.minigamepalooza.src.conversation.Conversation;

public abstract class Dialogue {
	private Conversation parent;
	private String message;
	
	public Dialogue(Conversation parent, String message) {
		this.parent = parent;
		this.message = message;
	}
	
	public Conversation getParent() {
		return this.parent;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public abstract void display(Player player);
}
