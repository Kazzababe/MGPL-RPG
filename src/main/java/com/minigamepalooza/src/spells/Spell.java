package com.minigamepalooza.src.spells;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

import com.minigamepalooza.src.classes.BaseClass;

public abstract class Spell {
	public static final Map<String, Spell> BY_NAME = new HashMap<String, Spell>();
	
	private String name;
	private String description;
	
	private int levelRequirement;
	
	private int resourceCost;
	private int resourceGeneration;
	
	private BaseClass requiredClass;
	
	public Spell(String name, int levelRequirement, int resourceCost, int resourceGeneration) {
		this.name = name;
		this.levelRequirement = levelRequirement;
		this.resourceCost = resourceCost;
		this.resourceGeneration = resourceGeneration;
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
	
	public abstract void use(Player player);
	
	static {
		BY_NAME.put("Wind Blast", new SpellWindBlast());
		BY_NAME.put("Snow Flurry", new SpellSnowFlurry());
		BY_NAME.put("Fireball", new SpellFireball());
	}
}
