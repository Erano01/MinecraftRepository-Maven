package me.erano.com.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener{
	
	@EventHandler
    public void breakBlock(BlockBreakEvent e) {
    	Player player = e.getPlayer();
    	if(!player.hasPermission("Bukkit.survival")) {
    		e.setCancelled(true);
    		player.sendMessage(ChatColor.RED+"Block Kirma Iznin Yok");
    	}
		
    }

}

