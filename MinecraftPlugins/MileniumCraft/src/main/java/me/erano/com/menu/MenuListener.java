package me.erano.com.menu;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class MenuListener implements Listener {
	
	private final MenuService menuService;

	public MenuListener(MenuService menuService) {
		this.menuService = menuService;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		this.menuService.handleClick(event);
	}
	@EventHandler
	public void onOpen(InventoryOpenEvent event) {
		this.menuService.handleOpen(event);
	}
	@EventHandler
	public void onClose(InventoryCloseEvent event) {
		this.menuService.handleClose(event);
	}
	
}
