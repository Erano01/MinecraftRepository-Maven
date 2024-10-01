package me.erano.com.core.examples;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.erano.com.core.CorePlugin;

public class ActionBarTask implements Runnable{

	private final CorePlugin plugin;

    public ActionBarTask(CorePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            plugin.getNMSHandler().sendActionBar(player, "Current TPS: " + ShowTPSCommand.formatTps(plugin.getNMSHandler().getTPS()[0]));
        }
    }
}
