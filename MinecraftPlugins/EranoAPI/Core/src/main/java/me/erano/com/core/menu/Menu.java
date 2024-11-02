package me.erano.com.core.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

//composite element of composite pattern for menu feature
public abstract class Menu implements Component{

    //private Inventory inventory;
    //private final Map<Integer, Button> buttonMap = new HashMap<>();





    abstract void onClick(InventoryClickEvent event);
    abstract void onOpen(InventoryOpenEvent event);
    abstract void onClose(InventoryCloseEvent event);
}
