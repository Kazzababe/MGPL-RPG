package com.minigamepalooza.src.conversation.dialogue;

import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.minigamepalooza.src.conversation.Conversation;

public class DialogueMessage extends Dialogue {
	public DialogueMessage(Conversation parent, String message) {
		super(parent, message);
	}

	@Override
	public void display(Player player) {
		this.getFancyMessage().send(player);
	}
	
	private FancyMessage getFancyMessage() {
		return new FancyMessage("[" + this.getParent().getNpcName() + "] ")
			.color(ChatColor.GOLD)
		.then(this.getMessage())
			.color(ChatColor.GRAY)
		.then(" >>>")
			.color(ChatColor.GREEN)
			.command("/next " + this.getParent().getStartTime());
	}
}
