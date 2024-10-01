package me.erano.com.listener.skill;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class KingArcherEvent implements Listener{
	
	@EventHandler
	public void onShoot(ProjectileHitEvent e) {
		Player player = (Player)e.getEntity().getShooter();
		if(player.hasPermission("Bukkitgames.kingarcher")&&!player.isOp()) {
			if(e.getEntity() instanceof Arrow) {
			Location lc = e.getEntity().getLocation();
			player.getWorld().createExplosion(lc, 2);
			e.getEntity().remove();
		}
	}
		
	}

}
