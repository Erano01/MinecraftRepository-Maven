package me.erano.com.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.erano.com.Plugin;
import me.erano.com.util.Database;

public class SetCoinCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("Bu Komut Yalnızca Oyuncular Tarafından Gönderilebilir");
			return true;
		}
		Player player = (Player)sender;
		
		if (args.length != 2) {
            player.sendMessage(ChatColor.RED + "Invalid usage. True format: /setcoin <playerName> <count>");
            return true;
        }
		if (!player.hasPermission("Bukkitgames.Admin")) {
            player.sendMessage(ChatColor.RED + "Bu komutu kullanmak için izniniz yok.");
            return true;
        }

        Player p = Plugin.getInstance().getServer().getPlayer(args[0]);
        if (p == null) {
            player.sendMessage(ChatColor.RED + "Bu isimde bir oyuncu bulunamadı.");
            return false;
        }

        int coin = 0;
        try {
            coin = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Coin sayısı tamsayı olmalıdır.");
            return false;
        }

        Database.setCoin(p.getUniqueId(), coin);
        player.sendMessage(ChatColor.RED + p.getName() + " oyuncusunun coin miktarı: " + ChatColor.GOLD + Database.getCoin(p.getUniqueId()));
        return true;
	}

	
}
