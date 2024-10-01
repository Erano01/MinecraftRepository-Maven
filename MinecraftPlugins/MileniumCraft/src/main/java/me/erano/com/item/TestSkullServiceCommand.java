package me.erano.com.item;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.erano.com.Plugin;

public class TestSkullServiceCommand implements CommandExecutor{
	
	private final Plugin plugin;

	public TestSkullServiceCommand(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		if(!player.hasPermission("OP")) {
			return true;
		}
		ItemStack head1 = plugin.getSkullService().getCustomHead(1);
		ItemStack head2 = plugin.getSkullService().getCustomHead(2);
		player.getInventory().addItem(head1);
		player.getInventory().addItem(head2);
		
		return true;
	}
}
