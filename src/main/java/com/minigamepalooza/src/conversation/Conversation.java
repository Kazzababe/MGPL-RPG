package com.minigamepalooza.src.conversation;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.minigamepalooza.src.conversation.dialogue.Dialogue;
import com.minigamepalooza.src.conversation.dialogue.DialogueMessage;
import com.minigamepalooza.src.conversation.dialogue.DialogueQuestion;

public class Conversation implements Cloneable {
	private String npcName;
	private long startTime;
	
	private List<Dialogue> dialogue = new ArrayList<Dialogue>();
	private int currentDialogue;
	
	private ConversationCallback callback;
	
	public Conversation(String npcName, ConversationCallback callback) {
		this.npcName = npcName;
		this.startTime = System.currentTimeMillis();
		this.callback = callback;
	}
	
	public String getNpcName() {
		return this.npcName;
	}
	
	public long getStartTime() {
		return this.startTime;
	}
	
	public Conversation message(String message) {
		this.dialogue.add(new DialogueMessage(this, message));
		return this;
	}
	
	public Conversation question(String question) {
		this.dialogue.add(new DialogueQuestion(this, question));
		return this;
	}
	
	public ConversationCallback getCallback() {
		return this.callback;
	}
	
	public Dialogue getDialogue() {
		return this.dialogue.get(this.currentDialogue);
	}
	
	public void display(Player player) {
		Dialogue dialogue = this.dialogue.get(this.currentDialogue);
		dialogue.display(player);
	}
	
	public void next(Player player) {
		this.currentDialogue++;
		if (this.currentDialogue >= this.dialogue.size()) {
			this.callback.onFinish(this, player);
		} else {
			this.display(player);
		}
	}
	
	@Override
	public Conversation clone() {
		try {
			return (Conversation) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public interface ConversationCallback {
		public void onFinish(Conversation conversation, Player player);
	}
}
