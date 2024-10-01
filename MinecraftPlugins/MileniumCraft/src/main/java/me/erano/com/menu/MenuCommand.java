package me.erano.com.menu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;

import me.erano.com.Plugin;
import me.erano.com.menu.impl.CustomerKitMenu;
import me.erano.com.menu.impl.PianoMenu;
import me.erano.com.menu.impl.RootKitEditMenu;

public class MenuCommand implements CommandExecutor {

	private final Plugin plugin;

	public MenuCommand(Plugin plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player player = (Player)sender;
		
		if("open".equals(args[0].toLowerCase()) && "piano".equals(args[1].toLowerCase())) {
			plugin.getMenuService().openMenu(new PianoMenu(plugin), player);
			return true;
		}
		if("open".equals(args[0].toLowerCase()) && "kits".equals(args[1].toLowerCase())) {
			plugin.getMenuService().openMenu(new CustomerKitMenu(plugin), player);
			return true;
		}
		if("set".equals(args[0].toLowerCase()) && "kits".equals(args[1].toLowerCase())) {
			if(!player.hasPermission("OP")) {
				return true;
			}
			plugin.getMenuService().openMenu(new RootKitEditMenu(plugin), player);
		}
		return true;
	}

}
