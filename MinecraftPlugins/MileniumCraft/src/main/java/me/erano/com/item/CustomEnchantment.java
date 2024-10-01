package me.erano.com.item;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.bukkit.enchantments.Enchantment;

public class CustomEnchantment {  
	
	public static final Enchantment MENU_ENCH = new EnchantmentWrapper("menu-ench","MENU_ENCH",1);

	public static void register() {
		boolean registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(MENU_ENCH);
		if(!registered) {
			registerEnchantment(MENU_ENCH);
		}
	}
	public static void registerEnchantment(Enchantment enchantment) {
		boolean registered = true;
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} catch (Exception e) {
			registered = false;
			e.printStackTrace();
		}
	}
}