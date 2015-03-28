package com.minigamepalooza.src.spells;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import com.minigamepalooza.src.utils.Utils;

public class SpellSnowFlurry extends Spell {

	public SpellSnowFlurry() {
		super("Snow Flurry", 3, 5, 0);
		
		this.setDescription("Pelts the targeted enemy with 6 snowballs");
	}

	@Override
	public void use(Player player) {
		LivingEntity entity = Utils.getTarget(player);
		if(entity == null) return;
		
		Location entityLoc = entity.getEyeLocation();
		for(int i = 0; i < 6; i++) {
			Location loc = entityLoc.clone().add(Math.random() * 4 - 2, Math.random() * 2, Math.random() * 4 - 2);
			Snowball snowball = player.getWorld().spawn(loc, Snowball.class);
			snowball.setVelocity(entityLoc.toVector().subtract(loc.toVector()).normalize());
			snowball.setShooter(player);
		}
		entity.damage(6, player);
	}
}
