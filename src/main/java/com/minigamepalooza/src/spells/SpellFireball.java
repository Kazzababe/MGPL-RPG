package com.minigamepalooza.src.spells;

import org.bukkit.entity.Player;

import com.minigamepalooza.src.projectiles.ProjectileFireball;

public class SpellFireball extends Spell {
	public SpellFireball() {
		super("Snow Flurry", 6,  2, 0, 2000);
		
		this.setDescription("Pelts the targeted enemy with 6 snowballs");
	}

	@Override
	public void use(Player player) {
		new ProjectileFireball(player);
	}
}