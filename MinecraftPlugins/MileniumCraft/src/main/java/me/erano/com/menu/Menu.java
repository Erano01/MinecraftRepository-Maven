package me.erano.com.menu;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.erano.com.Plugin;


public abstract class Menu implements MenuEventHandler {

	private final Inventory inventory;
	private final Map<Integer, MenuButton> buttonMap = new HashMap<>();
	protected Plugin plugin;

	public Menu(Plugin plugin) {
		this.plugin = plugin;
		this.inventory = this.createInventory();		
	}

	public Inventory getInventory() {
		return this.inventory;
	}

	public void addButton(int slot, MenuButton button) {
		this.buttonMap.put(slot, button);
	}

	// decorating menu
	public void displayTo(Player player) {
		this.buttonMap.forEach((slot, button) -> {
			ItemStack icon = button.getIconCreator().apply(player);
			this.inventory.setItem(slot, icon);
		});
	}

	@Override
	public void onClick(InventoryClickEvent event) {
		event.setCancelled(true);
		int slot = event.getSlot();
		MenuButton button = this.buttonMap.get(slot);
		if (button != null) {
			button.getEventConsumer().accept(event);
		}
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {
		this.displayTo((Player) event.getPlayer());
	}

	@Override
	public void onClose(InventoryCloseEvent event) {
	}

	protected abstract Inventory createInventory();

}
