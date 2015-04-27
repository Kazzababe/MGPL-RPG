package com.minigamepalooza.src.conversation.dialogue;

import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.minigamepalooza.src.conversation.Conversation;

public class DialogueAnswer extends Dialogue {
	private AnswerCallback callback;

	public DialogueAnswer(Conversation parent, String message, AnswerCallback callback) {
		super(parent, message);
		
		this.callback = callback;
	}
	
	public AnswerCallback getCallback() {
		return this.callback;
	}

	@Override
	public void display(Player player) {
		this.getFancyMessage().send(player);
	}
	
	private FancyMessage getFancyMessage() {
		return new FancyMessage(" - " + this.getMessage())
			.color(ChatColor.GRAY)
		.then(" >>>")
			.color(ChatColor.GREEN)
			.command("/respond " + this.getParent().getStartTime() + " " + this.getMessage());
	}
	
	public interface AnswerCallback {
		public void onClick(Conversation conversation, Player player);
	}
}
