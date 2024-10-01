package me.erano.com.kit;
import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Boxor implements IKit{

	@Override
	public String getKitName() {
		// TODO Auto-generated method stub
		return "boxor";
	}

	@Override
	public ArrayList<ItemStack> getItems() {
		ArrayList<ItemStack> items = new ArrayList<ItemStack>();
		ItemStack apple = new ItemStack(Material.APPLE);
		apple.setAmount(32);
		items.add(apple);

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
		return "Bukkitgames.boxor";
	}

}
