package me.erano.com.item;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class EnchantmentWrapper extends Enchantment{

	private final String name;
	private final int maxLvl;
	
	public EnchantmentWrapper(String namespace, String name, int lvl) {
		super(NamespacedKey.minecraft(namespace));
		this.name = name;
		this.maxLvl = lvl;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return maxLvl;
	}

	@Override
	public int getStartLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTreasure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCursed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canEnchantItem(ItemStack item) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
