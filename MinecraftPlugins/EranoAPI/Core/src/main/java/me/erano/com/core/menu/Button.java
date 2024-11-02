package me.erano.com.core.menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

//leaf element of composite pattern for menu feature
public abstract class Button implements Component{

    private ItemStack icon;
    private String name;

    @Override
    public void add(int index, Component component) {
        throw new UnsupportedOperationException("\"Leaf node doesn't support add() operation\"");
    }

    @Override
    public void remove(int index) {
        throw new UnsupportedOperationException("\"Leaf node doesn't support remove() operation\"");
    }

    @Override
    public Component getChildren(int index) {
        throw new UnsupportedOperationException("\"Leaf node doesn't support getChildren() operation\"");
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public ItemStack getIcon() {
        return this.icon;
    }
}
