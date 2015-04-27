package com.minigamepalooza.src.listeners;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.minigamepalooza.src.Forgotten;
import com.minigamepalooza.src.conversations.Conversation;
import com.minigamepalooza.src.conversations.Conversations;
import com.minigamepalooza.src.players.RPGPlayer;
import com.minigamepalooza.src.spells.Spell;

public class PlayerInteractListeners implements Listener {
	@EventHandler
	public void onPlayerItemHeld(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		RPGPlayer rpgPlayer = Forgotten.getRPGPlayer(player);
		
		if (event.getNewSlot() != 5) {
			switch (event.getNewSlot()) {
				case 0:
				case 1:
				case 2:
				case 3:
					Spell spell = null;
					try {
						spell = rpgPlayer.getLearnedSpells().get(event.getNewSlot());
					} catch (IndexOutOfBoundsException e) {}
					if (spell != null) {
						spell.use(player);
					}
					break;
				case 4:
					player.setHealth(player.getMaxHealth());
					break;
				case 6:
					break;
				case 8:
					break;
				default:
					break;
			}
			event.setCancelled(true);
			player.getInventory().setHeldItemSlot(5);
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntityEvent(NPCRightClickEvent event) {
		Player player = event.getClicker();
		NPC npc = event.getNPC();
		
		if (Conversation.NPC_CONVERSATIONS.containsKey(npc.getName())) {
			Conversation conversation = Conversation.NPC_CONVERSATIONS.get(npc.getName());
			Conversations.startConversation(player, conversation);
		}
	}
}
