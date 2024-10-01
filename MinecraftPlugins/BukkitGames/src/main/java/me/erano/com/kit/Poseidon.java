package me.erano.com.kit;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Poseidon implements IKit{

	@Override
	public String getKitName() {
		// TODO Auto-generated method stub
		return "poseidon";
	}

	@Override
	public ArrayList<ItemStack> getItems() {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		items.add(new ItemStack(Material.DIAMOND_SWORD));
		ItemStack beef = new ItemStack(Material.COOKED_BEEF);
		beef.setAmount(32);
		items.add(beef);
		items.add(new ItemStack(Material.WATER_BUCKET));
		
		return items;
	}

	@Override
	public ArrayList<ItemStack> getArmors() {
		ArrayList<ItemStack> armors = new ArrayList<ItemStack>();
		armors.add(new ItemStack(Material.DIAMOND_BOOTS));
		armors.add(new ItemStack(Material.DIAMOND_LEGGINGS));
		armors.add(new ItemStack(Material.DIAMOND_CHESTPLATE));
		armors.add(new ItemStack(Material.DIAMOND_HELMET));
		return armors;
	}

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return 80;
	}

	@Override
	public String getPermission() {
		// TODO Auto-generated method stub
		return "Bukkitgames.poseidon";
	}

}
