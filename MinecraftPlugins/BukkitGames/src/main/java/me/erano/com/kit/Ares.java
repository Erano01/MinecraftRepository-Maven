package me.erano.com.kit;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Ares implements IKit{

	@Override
	public String getKitName() {
		// TODO Auto-generated method stub
		return "ares";
	}

	@Override
	public ArrayList<ItemStack> getItems() {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(Material.IRON_SWORD));
		items.add(new ItemStack(Material.BOW));
		ItemStack arrow = new ItemStack(Material.ARROW);
		arrow.setAmount(64);
		items.add(arrow);
		return items;
	}
	
	@Override
	public ArrayList<ItemStack> getArmors() {
		ArrayList<ItemStack> armors = new ArrayList<ItemStack>();
		armors.add(new ItemStack(Material.CHAINMAIL_BOOTS));
		armors.add(new ItemStack(Material.CHAINMAIL_LEGGINGS));
		armors.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
		armors.add(new ItemStack(Material.CHAINMAIL_HELMET));
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
		return "Bukkitgames.ares";
	}

}
