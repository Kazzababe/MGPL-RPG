package com.minigamepalooza.src.players;

public class PlayerStats {
	private int level;
	private int experience;
	private int nextExperience;
	
	private int baseDamage;
	private int baseMovementSpeed;
	private int baseIntelligence;
	private int baseToughness;
	
	public int getLevel() {
		return this.level;
	}
	
	public int getExperience() {
		return this.experience;
	}
	
	public int getNextExperience() {
		return this.nextExperience;
	}
	
	public int getExperience(int level) {
		return (int) Math.floor(5 * Math.pow(level, 3) * Math.pow(2, level / 20));
	}
	
	public void incrementLevel(int amount) {
		this.level += amount;
	}
	
	public void incrementExperience(int amount) {
		this.experience += amount;
	}
	
	public void setNextExperience(int amount) {
		this.nextExperience = amount;
	}
	
	public int getBaseDamage() {
		return this.baseDamage;
	}
	
	public int getBaseMovementSpeed() {
		return this.baseMovementSpeed;
	}
	
	public int getBaseIntelligence() {
		return this.baseIntelligence;
	}
	
	public int getBaseToughness() {
		return this.baseToughness;
	}
	
	public void incrementBaseDamage(int amount) {
		this.baseDamage += amount;
	}
	
	public void incrementBaseMovementSpeed(int amount) { 
		this.baseMovementSpeed += amount;
	}
	
	public void incrementBaseIntelligence(int amount) {
		this.baseIntelligence += amount;
	}
	
	public void incrementBaseToughness(int amount) {
		this.baseToughness += amount;
	}
}
