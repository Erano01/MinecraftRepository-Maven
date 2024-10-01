package me.erano.com.kit;

import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachment;

import me.erano.com.Plugin;

public class KitManager {
	
	private static HashMap<Player,IKit> players = new HashMap<Player,IKit>();
	
	public static HashMap<Player,IKit> getPlayers(){
		return players;
	}
	public static void equipPlayerKit(Player player) {
			
			IKit kit = players.get(player);
			List<ItemStack> items = kit.getItems();
			ItemStack[] itemArray = new ItemStack[items.size()];
			items.toArray(itemArray);
			player.getInventory().addItem(itemArray);
				

	}
	public static void equipArmor(Player player) {
		
		IKit kit = players.get(player);
		List<ItemStack> armors = kit.getArmors();
		ItemStack[] armorArray = new ItemStack[armors.size()];
		armors.toArray(armorArray);
		player.getEquipment().setArmorContents(armorArray);
		
	}
	
	public static void setPermission(Player player,IKit kit) {
		
		
		
		if(getPlayers().containsKey(player)) {
			
			//remove perms from player
			PermissionAttachment pa = player.addAttachment(Plugin.getInstance());
			player.removeAttachment(pa);
			
			//givePermission will give new kit perms to player
			IKit oldKit = getPlayers().get(player);
			players.replace(player,oldKit,kit);
			
			
		}else {
			players.put(player, kit);
		}
		
		
		
		
	}
	public static void givePermission(Player player) {
		
			IKit kit = players.get(player);
			PermissionAttachment pa =player.addAttachment(Plugin.getInstance());
			String permString = kit.getPermission();
			pa.setPermission(permString,true);

	}
	public static void clearContent(Player player) {
		PermissionAttachment pa = player.addAttachment(Plugin.getInstance());
		player.removeAttachment(pa);
		
		player.getEquipment().clear();
		player.getInventory().clear();
		player.setHealth(20);
		player.setFoodLevel(20);
	}
}
