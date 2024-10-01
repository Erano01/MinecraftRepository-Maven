package me.erano.com.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.erano.com.Plugin;
import me.erano.com.util.Database;

public class CoinCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Bu Komut Yalnızca Oyuncular Tarafından Gönderilebilir");
			return true;
		}
		Player player = (Player)sender;
		
		if (args.length > 1) {
            player.sendMessage(ChatColor.RED + "Invalid usage. True format: /coin <playerName> or /coin");
            return true;
        }
		if(args.length == 0) {
			player.sendMessage(ChatColor.GREEN+"You have "+ChatColor.GOLD+Database.getCoin(player.getUniqueId())+ChatColor.GREEN+"coin");
			return true;
		}
		if(args.length==1) {
			Player p = Plugin.getInstance().getServer().getPlayer(args[0]);
			player.sendMessage(ChatColor.GREEN+p.getName()+" have "+ChatColor.GOLD+Database.getCoin(p.getUniqueId())+ChatColor.GREEN+"coin");
			return true;
		}
		return false;
	}

}
