package me.erano.com.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.erano.com.Plugin;
import net.md_5.bungee.api.ChatColor;

public class MoveEvent implements Listener{

	private int i =0;
	@EventHandler
	public void move(PlayerMoveEvent event) {
		
		if(i>50) {
			event.getPlayer().sendMessage(ChatColor.RED+"Oyun daha başlamadı hareket edemezsin !");
			i=0;
		}
        
        Player player = event.getPlayer();
        Location location = Plugin.locationPairs.get(player);
        if(player.getLocation().distance(location) >= 1) {
        	i++;
            player.teleport(location);
        }
		
	}
}
