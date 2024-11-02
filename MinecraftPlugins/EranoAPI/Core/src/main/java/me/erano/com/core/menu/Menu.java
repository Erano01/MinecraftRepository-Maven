package me.erano.com.core.menu;

import org.bukkit.Material;
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

    protected ItemStack icon;
    protected String name;
    protected final Map<Integer, Component> componentMap = new HashMap<>();

    public Menu(String name, ItemStack icon) {
        this.name = name;
        this.icon = icon;
        //this.inventory = this.createInventory();
    }

    @Override
    public void add(int index, Component component) {
        componentMap.put(index, component);
    }

    @Override
    public void remove(int index) {
        componentMap.remove(index);
    }

    @Override
    public Component getChildren(int index) {
        return componentMap.get(index);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ItemStack getIcon() {
        return this.icon;
    }

    public void onClick(InventoryClickEvent event){
        event.setCancelled(true);
        int slot = event.getSlot();
        Component component = componentMap.get(slot);
        if (component != null) {
            component.draw();
        }
    }
    abstract void onOpen(InventoryOpenEvent event);
    abstract void onClose(InventoryCloseEvent event);

    protected abstract Inventory createInventory();
}
