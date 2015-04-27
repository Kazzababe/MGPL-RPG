package com.minigamepalooza.src.conversation.dialogue;

import java.util.ArrayList;
import java.util.List;

import mkremins.fanciful.FancyMessage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.minigamepalooza.src.conversation.Conversation;
import com.minigamepalooza.src.conversation.dialogue.DialogueAnswer.AnswerCallback;

public class DialogueQuestion extends Dialogue {
	private List<DialogueAnswer> answers = new ArrayList<DialogueAnswer>();

	public DialogueQuestion(Conversation parent, String message) {
		super(parent, message);
	}

	@Override
	public void display(Player player) {
		this.getFancyMessage().send(player);
		for (DialogueAnswer answer : this.answers) {
			answer.display(player);
		}
	}
	
	private FancyMessage getFancyMessage() {
		return new FancyMessage("[" + this.getParent().getNpcName() + "] ")
			.color(ChatColor.GOLD)
		.then(this.getMessage())
			.color(ChatColor.GRAY);
	}
	
	public DialogueQuestion answer(String message, AnswerCallback callback) {
		this.answers.add(new DialogueAnswer(this.getParent(), message, callback));
		return this;
	}
	
	public List<DialogueAnswer> getAnswers() {
		return this.answers;
	}
}
