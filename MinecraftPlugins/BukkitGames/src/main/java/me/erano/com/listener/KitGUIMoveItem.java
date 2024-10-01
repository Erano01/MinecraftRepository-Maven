package me.erano.com.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.erano.com.kit.Ares;
import me.erano.com.kit.Boxor;
import me.erano.com.kit.Cannibal;
import me.erano.com.kit.Hades;
import me.erano.com.kit.KingArcher;
import me.erano.com.kit.KitManager;
import me.erano.com.kit.Poseidon;
import me.erano.com.kit.Tarzan;
import me.erano.com.kit.Vampire;
import me.erano.com.util.Database;

public class KitGUIMoveItem implements Listener {
	KitManager manager = new KitManager();
	
    @EventHandler
    public void clickEvent(InventoryClickEvent e){
        //if(e.getClickedInventory().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Kits")){
    	
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.AQUA+"Kits")) {
    		Player player = (Player) e.getWhoClicked();
    		long coin = Database.getCoin(player.getUniqueId());
    		
            switch(e.getCurrentItem().getType()){
            
                case REDSTONE:
                    if(coin >= 30) {
                    	player.closeInventory();
                    	KitManager.setPermission(player, new Vampire());
                        player.sendMessage(ChatColor.GREEN+KitManager.getPlayers().get(player).getKitName()+" Kitini Sectin");
                        coin =coin-30;
                        Database.setCoin(player.getUniqueId(), coin);
                        break;
                    }else {
                    	player.closeInventory();
                    	player.sendMessage(ChatColor.RED+"Yeterli coininiz yok");
                    	break;
                    }
                    
                case ARROW:
                	if(coin >=60) {
                		player.closeInventory();
                		KitManager.setPermission(player, new KingArcher());
                        player.sendMessage(ChatColor.RED+KitManager.getPlayers().get(player).getKitName()+" Kitini Sectin");
                        coin=coin-60;
                        Database.setCoin(player.getUniqueId(), coin);
                        break;
                	}
                	else {
                		player.closeInventory();
                		player.sendMessage(ChatColor.RED+"Yeterli coininiz yok");
                		break;
                	}
                case DIAMOND_CHESTPLATE:
                	if(coin >= 80) {
                		player.closeInventory();
                		KitManager.setPermission(player, new Poseidon());
                        player.sendMessage(ChatColor.RED+KitManager.getPlayers().get(player).getKitName()+" Kitini Sectin");
                        coin = coin-80;
                        Database.setCoin(player.getUniqueId(), coin);
                		break;
                	}else {
                		player.closeInventory();
                		player.sendMessage(ChatColor.RED+"Yeterli coininiz yok");
                		break;
                	}
                case BLAZE_POWDER:
                	if(coin >=60) {
                		player.closeInventory();
                		KitManager.setPermission(player, new Hades());
                    	player.sendMessage(ChatColor.RED+KitManager.getPlayers().get(player).getKitName()+" Kitini Sectin");
                        coin = coin-80;
                        Database.setCoin(player.getUniqueId(), coin);
                		break;
                	}
                	else {
                		player.closeInventory();
                		player.sendMessage(ChatColor.RED+"Yeterli coininiz yok");
                    	break;
                	}
                	
                case CHAINMAIL_CHESTPLATE:
                	player.closeInventory();
                	KitManager.setPermission(player, new Ares());
                	player.sendMessage(ChatColor.RED+KitManager.getPlayers().get(player).getKitName()+" Kitini Sectin");
                	break;
                case STICK:
                	player.closeInventory();
                	KitManager.setPermission(player, new Tarzan());
                	player.sendMessage(ChatColor.RED+KitManager.getPlayers().get(player).getKitName()+" Kitini Sectin");
                	break;
                case ROTTEN_FLESH:
                	player.closeInventory();
                	KitManager.setPermission(player, new Cannibal());
                	player.sendMessage(ChatColor.RED+KitManager.getPlayers().get(player).getKitName()+" Kitini Sectin");
                	break;
                case IRON_HELMET:
                	player.closeInventory();
                	KitManager.setPermission(player, new Boxor());
                	player.sendMessage(ChatColor.RED+KitManager.getPlayers().get(player).getKitName()+" Kitini Sectin");
                	break;
                default:
                	break;
            	}
    		
 
            	e.setCancelled(true);
        	}  
    }
 
}
