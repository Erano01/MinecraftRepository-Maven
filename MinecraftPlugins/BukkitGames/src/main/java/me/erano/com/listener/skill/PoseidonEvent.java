package me.erano.com.listener.skill;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoseidonEvent implements Listener{
	
	@EventHandler
	public void onSea(PlayerMoveEvent e) {
		if(e instanceof Player) {
			Player player = e.getPlayer();
			if(player.hasPermission("Bukkitgames.poseidon")&&!player.isOp()) {
				Material m = player.getLocation().getBlock().getType();
				if(m == Material.LEGACY_STATIONARY_WATER || m == Material.WATER) {
					//STATIONARY_WATER
					if(player.hasPermission("Bukkitgames.poseidon")) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10, 3));
					}
				}
				
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,10,3));
				
			}
		}
	}
	@EventHandler
	public void waterBreathing(EntityDamageEvent event) {
		//patlama ve yanma hasar�ndan koru
		if(event.getEntity() instanceof Player) {
        	Player player =(Player)event.getEntity();
        	if(player.hasPermission("Bukkitgames.poseidon")&&!player.isOp()){
        		if(event.getCause() == DamageCause.DROWNING) {
        			
        			player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING,10,3));	
        		}
        		//patlama korumas� z�rh� ver
        	}
        }
	}
}
