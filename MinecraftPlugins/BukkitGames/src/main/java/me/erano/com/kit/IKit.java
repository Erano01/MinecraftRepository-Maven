package me.erano.com.kit;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public interface IKit {
	
	String getKitName();
	ArrayList<ItemStack> getItems();
	ArrayList<ItemStack> getArmors();
	double price();
	String getPermission();
	
}
