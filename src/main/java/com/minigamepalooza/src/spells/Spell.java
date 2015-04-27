package com.minigamepalooza.src.spells;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.entity.Player;

import com.minigamepalooza.src.classes.BaseClass;
import com.minigamepalooza.src.classes.SubClass;

public abstract class Spell {
	public static final Map<String, Spell> BY_NAME = new HashMap<String, Spell>();
	public static final Spell SPELL_FIREBALL = new SpellFireball();
	public static final Spell SPELL_SNOWFLURRY = new SpellSnowFlurry();
	public static final Spell SPELL_WINDBLAST = new SpellWindBlast();
	
	private String name;
	private String description;
	
	private int levelRequirement;
	
	private int resourceCost;
	private int resourceGeneration;
	private long cooldown;
	
	private BaseClass requiredClass;
	private Set<SubClass> subClasses;
	
	public Spell(String name, int levelRequirement, int resourceCost, int resourceGeneration, long cooldown) {
		this.name = name;
		this.levelRequirement = levelRequirement;
		this.resourceCost = resourceCost;
		this.resourceGeneration = resourceGeneration;
		this.cooldown = cooldown;
		
		this.subClasses = new HashSet<SubClass>();
		
		BY_NAME.put(name, this);
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getLevelRequirement() {
		return this.levelRequirement;
	}
	
	public int getResourceCost() {
		return this.resourceCost;
	}
	
	public int getResourceGeneration() {
		return this.resourceGeneration;
	}
	
	public long getCooldown() {
		return this.cooldown;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public BaseClass getRequiredClass() {
		return this.requiredClass;
	}
	
	protected void setDescription(String description) {
		this.description = description;
	}
	
	protected void setRequiredClass(BaseClass requiredClass) {
		this.requiredClass = requiredClass;
	}
	
	protected void setSubClasses(SubClass... classes) {
		this.subClasses.addAll(Arrays.asList(classes));
	}
	
	public abstract void use(Player player);
}
