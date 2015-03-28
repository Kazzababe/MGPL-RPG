package com.minigamepalooza.src.projectiles;

import org.bukkit.entity.Player;

public abstract class ProjectileParticles extends Projectile {

	public ProjectileParticles(Player player, int lifeSpan) {
		super(player.getUniqueId(), lifeSpan);
	}
}
