package me.erano.com.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public interface MenuEventHandler {
	void onClick(InventoryClickEvent event);
	void onOpen(InventoryOpenEvent event);
	void onClose(InventoryCloseEvent event);
}
