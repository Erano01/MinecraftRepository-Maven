package me.erano.com.core.menu;

import org.bukkit.inventory.ItemStack;

//component element of composite pattern for menu feature
public interface Component {
    void add(int index,Component component);
    void remove(int index);
    Component getChildren(int index);

}
