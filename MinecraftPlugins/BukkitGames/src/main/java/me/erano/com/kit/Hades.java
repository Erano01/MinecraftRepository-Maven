package me.erano.com.kit;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Hades implements IKit{

	@Override
	public String getKitName() {
		// TODO Auto-generated method stub
		return "hades";
	}

	@Override
	public ArrayList<ItemStack> getItems() {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(Material.IRON_SWORD));
		ItemStack beef = new ItemStack(Material.COOKED_BEEF);
		beef.setAmount(32);
		items.add(beef);
		items.add(new ItemStack(Material.LAVA_BUCKET));
		return items;
	}

	@Override
	public ArrayList<ItemStack> getArmors() {
		ArrayList<ItemStack> armors = new ArrayList<ItemStack>();
		armors.add(new ItemStack(Material.IRON_BOOTS));
		armors.add(new ItemStack(Material.IRON_LEGGINGS));
		armors.add(new ItemStack(Material.IRON_CHESTPLATE));
		armors.add(new ItemStack(Material.IRON_HELMET));
		return armors;
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return 60;
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "Bukkitgames.hades";
	}

}
