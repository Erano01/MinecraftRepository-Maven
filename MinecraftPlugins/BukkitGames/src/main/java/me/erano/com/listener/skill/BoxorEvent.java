package me.erano.com.listener.skill;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BoxorEvent implements Listener{
	
	@EventHandler
	public void boxingGloves(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player)e.getDamager();
			if(p.hasPermission("Bukkitgames.boxor")&&!p.isOp()) {
				if(p.getItemInHand()==null) {
				e.setDamage(6);
				}
			}
		}
		
	}
	@EventHandler
	public void onWalk(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if(player.hasPermission("Bukkitgames.boxor")&&player.isOnGround()&&!player.isOp()) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,10,3));
		}
	}

}
