package me.erano.com.kit;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import me.erano.com.item.CustomEnchantment;
import net.md_5.bungee.api.ChatColor;

public class KitService {

	private final JavaPlugin plugin;
	private File file;

	public KitService(JavaPlugin plugin) {
		this.plugin = plugin;
		if (!isFileExist()) {
			this.file = loadDefaultFile();
		}
		
	}

	public List<Kit> getKitListFromConfig() {
		FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
		ConfigurationSection root = fileConfig.getConfigurationSection("kits");
		Set<String> kitsStringKeySet = root.getKeys(false);
		List<Kit> kitList = new ArrayList<Kit>();
		for (String key : kitsStringKeySet) {
			ConfigurationSection tmpKitConfig = root.getConfigurationSection(key);
			Kit kit = new Kit.KitBuilder(
					1,
					tmpKitConfig.getName(),
					tmpKitConfig.getString("display-name"),
					(List<String>) tmpKitConfig.getList("menu-lore"))
					.price(tmpKitConfig.getInt("price"))
					.items(parseItemsFromRequestedKit(tmpKitConfig, "item-list"))
					.armors(parseItemsFromRequestedKit(tmpKitConfig, "armor-list"))
					.permission(tmpKitConfig.getString("permission-string"))
					.icons(parseIconsFromRequestedKit(tmpKitConfig))
					.build();
			kitList.add(kit);
		}
		return kitList;
	}

	private List<ItemStack> parseItemsFromRequestedKit(ConfigurationSection section, String sectionName) {
		ConfigurationSection itemListSection = section.getConfigurationSection(sectionName);
		Set<String> itemListKeySet = itemListSection.getKeys(false);
		List<ItemStack> itemList = new ArrayList<ItemStack>();
		// for each item
		for (String key : itemListKeySet) {
			ConfigurationSection itemSectionTmp = itemListSection.getConfigurationSection(key);
			Material material = Material.matchMaterial(itemSectionTmp.getName());
			if (material == null) {
				continue;
			}
			ItemStack item = new ItemStack(material, itemSectionTmp.getInt("count"));
			// for each ench
			ConfigurationSection enchsSection = itemSectionTmp.getConfigurationSection("enchs");
			Set<String> enchsSectionKeys = enchsSection.getKeys(false);
			for (String enchStrKey : enchsSectionKeys) {
				ConfigurationSection enchTmp = enchsSection.getConfigurationSection(enchStrKey);
				Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchTmp.getName().toLowerCase(Locale.ROOT)));
				item.addEnchantment(enchantment, enchTmp.getInt("ench-level"));
			}
			itemList.add(item);
		}
		return itemList;
	}

	private List<ItemStack> parseIconsFromRequestedKit(ConfigurationSection section) {
		// effect lore and display name for each icon
		ConfigurationSection iconListSection = section.getConfigurationSection("icons");
		Set<String> iconListKeySet = iconListSection.getKeys(false);
		List<ItemStack> iconList = new ArrayList<ItemStack>();
		for (String key : iconListKeySet) {
			ConfigurationSection iconSectionTmp = iconListSection.getConfigurationSection(key);
			Material material = Material.matchMaterial(iconSectionTmp.getName());
			if (material == null) {
				continue;
			}
			ItemStack item = new ItemStack(material, iconSectionTmp.getInt("count"));
			if (iconSectionTmp.getBoolean("custom-ench") == true) {
				item.addUnsafeEnchantment(CustomEnchantment.MENU_ENCH, 1);
			}
			iconList.add(item);
		}
		return iconList;
	}

	// validating is file exists
	private boolean isFileExist() {
		File file = new File(plugin.getDataFolder(), "kits.yml");
		return file.exists();
	}

	// copying datas from src/main/resources/kits.yml
	// to server/plugins/myplugin/kits.yml
	public File loadDefaultFile() {
		File file = new File(plugin.getDataFolder(), "kits.yml");
		YamlConfiguration config = YamlConfiguration
				.loadConfiguration(new InputStreamReader(plugin.getResource("kits.yml")));
		saveFile(config, file);
		return file;
	}

	// saving configurations to given file
	private void saveFile(FileConfiguration fileConfig, File file) {
		if (fileConfig == null || file == null) {
			plugin.getServer().broadcast(ChatColor.RED + "Failed to save file!", "OP");
			return;
		}
		try {
			fileConfig.save(file);
		} catch (IOException e) {
			plugin.getLogger().severe("Failed to save file: " + e.getMessage());
		}
	}
	public File getFile() {
		return file;
	}
}
