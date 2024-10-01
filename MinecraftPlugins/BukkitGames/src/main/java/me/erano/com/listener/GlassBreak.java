package me.erano.com.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class GlassBreak implements Listener{

	@EventHandler
	public void glassBreak(PlayerInteractEvent e) {
		if(e.getClickedBlock().getType().equals(Material.GLASS)) {
			e.setCancelled(true);
		}
	}
}
