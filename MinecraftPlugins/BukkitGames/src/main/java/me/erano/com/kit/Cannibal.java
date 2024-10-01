package me.erano.com.kit;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Cannibal implements IKit{

	@Override
	public String getKitName() {
		// TODO Auto-generated method stub
		return "Cannibal";
	}

	@Override
	public ArrayList<ItemStack> getItems() {
		// TODO Auto-generated method stub
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(Material.STONE_SWORD));
		return items;
	}

	@Override
	public ArrayList<ItemStack> getArmors() {
		ArrayList<ItemStack> armors = new ArrayList<ItemStack>();
		armors.add(new ItemStack(Material.LEATHER_BOOTS));
		armors.add(new ItemStack(Material.LEATHER_LEGGINGS));
		armors.add(new ItemStack(Material.LEATHER_CHESTPLATE));
		armors.add(new ItemStack(Material.LEATHER_HELMET));
		return armors;
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "Bukkitgames.cannibal";
	}

}
