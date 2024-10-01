package me.erano.com.menu.impl;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.erano.com.Plugin;
import me.erano.com.kit.Kit;
import me.erano.com.menu.Menu;
import me.erano.com.menu.MenuButton;
import net.md_5.bungee.api.ChatColor;

public class CustomerKitMenu extends Menu {

	public CustomerKitMenu(Plugin plugin) {
		super(plugin);
	}

	@Override
	protected Inventory createInventory() {
		return Bukkit.createInventory(null, 45, "Kits");
	}

	//itemleri gösterme kısmında sıkıntı var
	@Override
	public void displayTo(Player player) {
		int inventorySize = this.getInventory().getSize();
		List<Kit> kits = plugin.getKitService().getKitListFromConfig();
		if (kits.size() == 0) {
			super.displayTo(player);
			return;
		}
		int firstSlot = (inventorySize - kits.size()) / 2;
		for (int i = 0; i < kits.size(); i++) {
			Kit kit = kits.get(i);
			//get icons for one kit. //List<ItemStack> icons = kit.getIcons();
			ItemStack icon = kit.getIcons().get(0);
			this.addButton(firstSlot + i, createKitButton(icon,kit));
		}
		if(player.hasPermission("OP")) {
			int lastSlot = inventorySize - 1;
			this.addButton(lastSlot, createKitEditButton(new ItemStack(Material.DIAMOND_AXE)));
		}
		super.displayTo(player);
	}

	private MenuButton createKitButton(ItemStack itemStack,Kit kit) {
		return new MenuButton()
			.creator(player -> itemStack)
			.consumer(event -> { 
				Player player = (Player) event.getWhoClicked();

				int playerCoin = plugin.getYamlPlayerService().getPlayerCoin(player.getUniqueId());
				if(kit.getPrice() > playerCoin) {
					player.sendMessage(ChatColor.RED+"You dont have enaugh money to buy this kit");
					return;
				}
				plugin.getYamlPlayerService().updatePlayerCoin(player.getUniqueId(), playerCoin-kit.getPrice());
				player.sendMessage(ChatColor.GREEN+"Success");
			});
	}
	private MenuButton createKitEditButton(ItemStack itemStack) {
		return new MenuButton()
				.creator(player -> itemStack)
				.consumer(event -> {
					Player player = (Player) event.getWhoClicked();
					plugin.getMenuService().openMenu(new PianoMenu(plugin), player);
					
				});
	}

}
