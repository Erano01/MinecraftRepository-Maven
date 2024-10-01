package me.erano.com.listener.skill;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;

public class CannibalEvent implements Listener{
	
	@EventHandler
	public void eatThem(PlayerDeathEvent event){
		Player victim =event.getEntity();
		if(victim.getKiller()!=null) {
			Player killer = victim.getKiller();
			if(killer.hasPermission("Bukkitgames.cannibal")&&!killer.isOp()) {
				killer.setHealth(killer.getHealth()+10);
				killer.setFoodLevel(20);
				victim.sendMessage(ChatColor.RED+killer.getName()+ChatColor.GREEN+" yamyam seni yedi!.");
			}
		}
		
	}
}
