package me.erano.com.economy;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.erano.com.Plugin;

public class PlayerJoinListener implements Listener {

	private final YamlPlayerService service;

	public PlayerJoinListener(YamlPlayerService service) {
		this.service = service;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		service.registerPlayer(player);
	}
	
}
