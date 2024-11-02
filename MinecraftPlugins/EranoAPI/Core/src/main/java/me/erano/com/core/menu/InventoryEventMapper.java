package me.erano.com.core.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryEventMapper {

    private Map<Inventory, Menu> registeredMenus = new HashMap<>();

    public void openMenu(Menu menu, Player player) {
        this.registerHandledInventory(menu.getInventory(), menu);
        player.openInventory(menu.getInventory());
    }

    public void registerHandledInventory(Inventory inventory, Menu menu) {
        this.registeredMenus.put(inventory, menu);
    }

    public void unregisterInventory(Inventory inventory) {
        this.registeredMenus.remove(inventory);
    }

    public void handleClick(InventoryClickEvent event) {
        Menu menu = this.registeredMenus.get(event.getInventory());
        if (menu != null) {
            menu.onClick(event);
        }
    }

    public void handleOpen(InventoryOpenEvent event) {
        Menu menu = this.registeredMenus.get(event.getInventory());
        if (menu != null) {
            menu.onOpen(event);
        }
    }

    public void handleClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        Menu menu = this.registeredMenus.get(inventory);
        if (menu != null) {
            menu.onClose(event);
            this.unregisterInventory(inventory);
        }
    }
}
