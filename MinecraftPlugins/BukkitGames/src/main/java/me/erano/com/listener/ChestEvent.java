package me.erano.com.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;

public class ChestEvent implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void openChest(PlayerInteractEvent e) {
		if(e.getClickedBlock().getType().equals(Material.CHEST)) {
			Player player = e.getPlayer();
			e.setCancelled(true);
			player.sendMessage(ChatColor.RED+"Game isn't started wait for few seconds");
		}

		
	}
}
