package com.minigamepalooza.src.classes;

public class BaseClass {
	private String name;
	private String description;
	
	public BaseClass(String name) {
		this.name = name;
	}
	
	protected void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDescription() {
		return this.description;
	}
}
