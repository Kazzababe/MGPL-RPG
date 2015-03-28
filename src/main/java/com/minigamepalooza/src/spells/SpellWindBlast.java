package com.minigamepalooza.src.spells;

import org.bukkit.entity.Player;

import com.minigamepalooza.src.projectiles.ProjectileWindBlast;

public class SpellWindBlast extends Spell {
	public SpellWindBlast() {
		super("Wind Blast", 1, 0, 2);
		
		this.setDescription("Shoots a heavy blast of air towards an enemy");
	}

	@Override
	public void use(Player player) {
		new ProjectileWindBlast(player);
	}
}
