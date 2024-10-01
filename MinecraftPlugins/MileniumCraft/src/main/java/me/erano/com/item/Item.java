package me.erano.com.item;

import java.util.List;

import net.minecraft.server.v1_16_R3.Enchantment;
import net.minecraft.server.v1_16_R3.Material;
import net.minecraft.server.v1_16_R3.NBTTagCompound;


public class Item {
	
	//each item have unique NBTTag
	//find Items unique identifier variable type in NMS(NBT, NBTCompound, NBTInt ??).
	private NBTTagCompound nbtTagCompound;
	
	private String name;
	
	private Material material;
	
	private Integer count;
	
	private List<Enchantment> enchantments;
	
	private List<String> lore;
	
	private Item() {
		
	}
	private static class ItemBuilder{
		
		private String name;
		
		private Material material;
		
		private Integer count;
		
		private List<Enchantment> enchantments;
		
		private List<String> lore;
		
		// builder methods
		
		
	}
	
	public String getName() {
		return name;
	}
	public Material getMaterial() {
		return material;
	}
	public Integer getCount() {
		return count;
	}
	public List<Enchantment> getEnchantments() {
		return enchantments;
	}
	public List<String> getLore() {
		return lore;
	}
	
	

}
