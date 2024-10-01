package me.erano.com.menu;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class MenuService {
	
	private final Map<Inventory,MenuEventHandler> activeMenus = new HashMap<>();

	  public void openMenu(Menu menu, Player player) {
	    this.registerHandledInventory(menu.getInventory(), menu);
	    player.openInventory(menu.getInventory());
	  }

	  public void registerHandledInventory(Inventory inventory, MenuEventHandler handler) {
	    this.activeMenus.put(inventory, handler);
	  }

	  public void unregisterInventory(Inventory inventory) {
	    this.activeMenus.remove(inventory);
	  }

	  public void handleClick(InventoryClickEvent event) {
	    MenuEventHandler handler = this.activeMenus.get(event.getInventory());
	    if (handler != null) {
	      handler.onClick(event);
	    }
	  }

	  public void handleOpen(InventoryOpenEvent event) {
	    MenuEventHandler handler = this.activeMenus.get(event.getInventory());
	    if (handler != null) {
	      handler.onOpen(event);
	    }
	  }

	  public void handleClose(InventoryCloseEvent event) {
	    Inventory inventory = event.getInventory();
	    MenuEventHandler handler = this.activeMenus.get(inventory);
	    if (handler != null) {
	      handler.onClose(event);
	      this.unregisterInventory(inventory);
	    }
	  }
}
