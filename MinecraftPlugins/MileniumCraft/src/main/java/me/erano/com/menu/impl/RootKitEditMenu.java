package me.erano.com.menu.impl;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.erano.com.Plugin;
import me.erano.com.menu.Menu;
import me.erano.com.menu.MenuButton;

public class RootKitEditMenu extends Menu{

	// Adminlerin kullanacağı ve her kite özel edit modunun açılacağı root menu burası olacak
	// bu menuden sonra açılan sub menu 
	public RootKitEditMenu(Plugin plugin) {
		super(plugin);
		
	}

	@Override
	protected Inventory createInventory() {
		return Bukkit.createInventory(null, 45, "Kit Edit Menu");
	}
	
	@Override
	public void displayTo(Player player) {
		int inventorySize = this.getInventory().getSize();
		float basePitch = 0.5f;

		for (int i = 0; i < inventorySize; i++) {
			Material material = i % 2 == 0 ? Material.WHITE_STAINED_GLASS_PANE : Material.BLACK_STAINED_GLASS_PANE;
			final float pitchOffset = i * 0.033f;
			this.addButton(i, this.createPianoButton(basePitch + pitchOffset, material));
		}

		super.displayTo(player);
	}

	private MenuButton createPianoButton(float pitch, Material material) {
		return new MenuButton()
			.creator(player -> new ItemStack(material))
			.consumer(event -> { 
				Player player = (Player) event.getWhoClicked();
				Location loc = player.getEyeLocation();
				player.playSound(loc, Sound.BLOCK_NOTE_BLOCK_PLING, 1f, pitch);
			});
	}

}
