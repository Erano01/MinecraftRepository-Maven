package me.erano.com.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.erano.com.Plugin;
import me.erano.com.kit.KitManager;

public class JoinEvent implements Listener{

	
	//private static main plugin = main.getInstance();
	
	private static int i = 0;
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
        
		KitManager.clearContent(player);
		
		if(Plugin.getInstance().isGamePlaying==true) {
			player.setGameMode(GameMode.SPECTATOR);
			player.teleport(player.getLastDeathLocation());
		}else {
			player.setGameMode(GameMode.SURVIVAL);
			Plugin.locationPairs.put(player, Plugin.spawnLocations.get(i));
			player.teleport(Plugin.spawnLocations.get(i));
			i++;
		}
		
		
	}
}
