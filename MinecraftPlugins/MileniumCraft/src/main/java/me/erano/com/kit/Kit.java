package me.erano.com.kit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;

public class Kit {

	private int id;

	private String name;

	private String displayName;
	
	private List<String> lore;
	
	private int price;

	private List<ItemStack> items;

	private List<ItemStack> armors;

	private String permission;

	private List<ItemStack> icons;

	private Kit() {
		// Private constructor to force the use of Builder
	}

	public static class KitBuilder {
		private int id;
		private String name;
		private String displayName;
		private List<String> lore;
		private int price;
		private List<ItemStack> items;
		private List<ItemStack> armors;
		private String permission;
		private List<ItemStack> icons;

		public KitBuilder(int id, String name, String displayName, List<String> lore) {
			this.id = id;
			this.name = name;
			this.displayName = displayName;
			this.lore = lore;
		}

		public KitBuilder items(List<ItemStack> items) {
			this.items = items;
			return this;
		}

		public KitBuilder armors(List<ItemStack> armors) {
			this.armors = armors;
			return this;
		}

		public KitBuilder price(int price) {
			this.price = price;
			return this;
		}

		public KitBuilder permission(String permission) {
			this.permission = permission;
			return this;
		}

		public KitBuilder icons(List<ItemStack> icons) {
			this.icons = icons;
			return this;
		}

		public Kit build() {
			Kit kit = new Kit();
			kit.id = this.id;
			kit.name = this.name;
			kit.displayName = this.displayName;
			kit.lore = this.lore;
			kit.items = this.items;
			kit.armors = this.armors;
			kit.price = this.price;
			kit.permission = this.permission;
			kit.icons = this.icons;
			return kit;
		}
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public List<String> getLore() {
		return lore;
	}

	public int getPrice() {
		return price;
	}

	public List<ItemStack> getItems() {
		return items;
	}

	public List<ItemStack> getArmors() {
		return armors;
	}

	public String getPermission() {
		return permission;
	}

	public List<ItemStack> getIcons() {
		return icons;
	}
	

}
