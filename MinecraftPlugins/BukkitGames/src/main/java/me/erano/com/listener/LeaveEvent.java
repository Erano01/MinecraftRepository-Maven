package me.erano.com.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import me.erano.com.Plugin;

public class LeaveEvent implements Listener{

//	private static main plugin = main.getPlugin(main.class);
	private static Plugin plugin = Plugin.getInstance();
	
	@EventHandler
	public void leave(PlayerQuitEvent e) {
		Player player =e.getPlayer();
		PermissionAttachment pa = player.addAttachment(plugin);
		player.removeAttachment(pa);
		Plugin.locationPairs.remove(player);
	}
}
