package com.minigamepalooza.src.players;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.minigamepalooza.base.player.PaloozaPlayer;
import com.minigamepalooza.src.Forgotten;
import com.minigamepalooza.src.items.Items;
import com.minigamepalooza.src.spells.Spell;

public class RPGPlayer {
	private final String playerName;
	private final UUID uniqueId;
	
	private PlayerStats stats;
	
	private List<Spell> learnedSpells = new ArrayList<Spell>();
	
	public RPGPlayer(Player player) {
		this.playerName = player.getName();
		this.uniqueId = player.getUniqueId();
		
		this.stats = new PlayerStats();
		
		this.setDefaultInventory();
		this.checkExperience();
	}
	
	public Player getPlayer() {
		return Forgotten.getPlayer(this.uniqueId).getPlayer();
	}
	
	public PaloozaPlayer getPaloozaPlayer() {
		return Forgotten.getPlayer(this.uniqueId);
	}
	
	public boolean isOnline() {
		return this.getPaloozaPlayer().isOnline();
	}
	
	public String getName() {
		return this.playerName;
	}
	
	public PlayerStats getStats() {
		return this.stats;
	}
	
	public List<Spell> getLearnedSpells() {
		return this.learnedSpells;
	}
	
	public void addSpell(Spell spell) {
		this.learnedSpells.add(spell);
	}
	
	public void setDefaultInventory() {
		Player player = this.getPlayer();
		PlayerInventory inv = player.getInventory();
		
		inv.setItem(0, null);
		inv.setItem(1, null);
		inv.setItem(2, null);
		inv.setItem(3, null);
		inv.setItem(4, new ItemStack(Material.POTION, 1, (byte) 21));
		inv.setItem(5, new ItemStack(Material.IRON_SWORD));
		inv.setItem(6, new ItemStack(Material.BOOK));
		inv.setItem(7, Items.PLACEHOLDER.clone());
		inv.setItem(8, new ItemStack(Material.SKULL_ITEM));
	}
	
	public void checkExperience() {
		int level = this.stats.getLevel();
		int nextLevel = level + 1;
		
		float minExperience = this.stats.getExperience(level - 1);
		float experience = this.stats.getExperience();
		float nextExperience = this.stats.getNextExperience();
		
		float exp = (experience - minExperience) / (nextExperience - minExperience);
		this.getPlayer().setExp(exp);
		
		if(experience >= nextExperience) {
			this.stats.setNextExperience(this.stats.getExperience(nextLevel));
			this.levelUp();
			
			checkExperience();
			//Recall this method purely to re-calculate the exp bar properly
		}
	}
	
	public void levelUp() {
		this.stats.incrementLevel(1);
		this.getPlayer().sendMessage(ChatColor.YELLOW + "Congratulations " + this.playerName + ", on reaching level " + this.stats.getLevel() + "!");
		this.getPlayer().playSound(this.getPlayer().getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
		
		for(Spell spell : Spell.BY_NAME.values()) {
			if(this.stats.getLevel() >= spell.getLevelRequirement() && !this.learnedSpells.contains(spell)) {
				this.learnedSpells.add(spell);
			}
		}
	}
}
