package me.erano.com.core.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//composite element of composite pattern for menu feature
public abstract class Menu implements Component{

    //private Inventory inventory;

    private ItemStack icon;
    private String name;
    private final List<Component> componentList = new ArrayList<Component>();
    private final Map<Integer, Component> iconMap = new HashMap<>();

    @Override
    public void draw() {

    }

    @Override
    public void add(Component component) {
        componentList.add(component);
    }

    @Override
    public void remove(Component component) {
        componentList.remove(component);
    }

    @Override
    public Component getChildren(int index) {
        return componentList.get(index);
    }

    abstract void onClick(InventoryClickEvent event);
    abstract void onOpen(InventoryOpenEvent event);
    abstract void onClose(InventoryCloseEvent event);
}
