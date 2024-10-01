package me.erano.com.listener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.erano.com.Plugin;
import me.erano.com.kit.KitManager;
import me.erano.com.util.Database;
import net.md_5.bungee.api.ChatColor;

public class DeathEvent implements Listener{
	
	@EventHandler
	public void death(PlayerDeathEvent e) {
		Player victim = e.getEntity();
		Player killer = victim.getKiller();
		
		victim.setGameMode(GameMode.SPECTATOR);
		
		Plugin.locationPairs.remove(victim);
		KitManager.clearContent(victim);
		
		if(Plugin.getInstance().getConfig().getBoolean("hooks.bungeecord")) {
			//send players to lobby
			Plugin.getInstance().getServer().broadcastMessage(ChatColor.GREEN+killer.getName()+" +2 coin kazandı");
			sendPlayer(victim,Plugin.getInstance().getConfig().getString("hooks.sendPlayerToServer"));
			Database.setCoin(killer.getUniqueId(), Database.getCoin(killer.getUniqueId())+2);
		}else {
			Plugin.getInstance().getServer().getPlayer(victim.getUniqueId()).kickPlayer(killer.getName()+" tarafından öldürüldün");
		}
		
		

	}
	public void sendPlayer(Player player,String serverName) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
		try {
			dataOutputStream.writeUTF("Connect");
			dataOutputStream.writeUTF(serverName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		player.sendPluginMessage(Plugin.getInstance(), "BungeeCord", byteArrayOutputStream.toByteArray());
	}
}
