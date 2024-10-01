package me.erano.com.item;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.md_5.bungee.api.ChatColor;

//This class is indexed to work with a specific yml file. 
//If the specific file is not in the plugin data folder, it creates this yml file itself.
//If the file exists and there are typo in its content, fixes its typos
public class SkullService {

	private final JavaPlugin plugin;
	private File file;

	public SkullService(JavaPlugin plugin) {
		this.plugin = plugin;
		if (!isFileExist()) {
			this.file = loadDefaultFile();
		}
		this.file = verifyContent(new File(plugin.getDataFolder(), "custom-heads.yml"));
	}

	public ItemStack getCustomHead(int requestID) {
		ConfigurationSection requestedConfigurationSection = getHeadConfigurationSection(requestID);
		if (requestedConfigurationSection == null) {
			this.plugin.getLogger().severe("There is no head config with this id : " + requestID + " -> null");
			return null;
		}
		int id = requestedConfigurationSection.getInt("id");
		String name = requestedConfigurationSection.getString("name");
		String minecraftURL = requestedConfigurationSection.getString("minecraft-url");
		String value = requestedConfigurationSection.getString("value");
		ConfigurationSection loreSection = requestedConfigurationSection.getConfigurationSection("lore");
		String menuLoreDesc = loreSection.getConfigurationSection("in-menu-lore").getString("description");
		String handLoreDesc = loreSection.getConfigurationSection("in-hand-lore").getString("description");

		if (name.equals(null) || minecraftURL.equals(null) || value.equals(null) || menuLoreDesc.equals(null)
				|| handLoreDesc.equals(null)) {
			this.plugin.getLogger().severe("Required variables are missing in custom-heads.yml");
			return null;
		}

		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
		GameProfile profile = new GameProfile(UUID.randomUUID(), name);
		profile.getProperties().put("textures", new Property("textures", value));
		try {
			Field profileField = skullMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(skullMeta, profile);

		} catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException error) {
			this.plugin.getLogger().severe("There is no match for requested configuration skull data on internet");
			error.printStackTrace();
			return null;
		}
		skullMeta.setDisplayName(name);
		skullMeta.setLore(Arrays.asList(menuLoreDesc, handLoreDesc));
		skullMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_UNBREAKABLE);
		skull.setItemMeta(skullMeta);

		return skull;

	}

	private ConfigurationSection getHeadConfigurationSection(int id) {
		if (!isHeadExist(id)) {
			this.plugin.getLogger().severe("There is no head config with this id : " + id + " -> null");
			return null;
		}
		YamlConfiguration fileConfig = YamlConfiguration.loadConfiguration(this.file);
		ConfigurationSection root = fileConfig.getConfigurationSection("custom-heads");
		Set<String> rootKeys = root.getKeys(false);
		for (String key : rootKeys) {
			ConfigurationSection headSection = root.getConfigurationSection(key);
			if (headSection.getInt("id") == id) {
				return headSection;
			}
		}
		return null;
	}

	// validating data folder for skull which is match with given unique id 
	private boolean isHeadExist(int id) {
		YamlConfiguration fileConfig = YamlConfiguration.loadConfiguration(this.file);
		ConfigurationSection root = fileConfig.getConfigurationSection("custom-heads");
		Set<String> rootKeys = root.getKeys(false);
		for (String key : rootKeys) {
			ConfigurationSection headSection = root.getConfigurationSection(key);
			if (headSection.getInt("id") == id) {
				return true;
			}
		}
		return false;
	}

	// validating given file content
	private File verifyContent(File file) {
		FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
		ConfigurationSection root = fileConfig.getConfigurationSection("custom-heads");
		if (root == null) {
			return loadDefaultFile();
		}
		Set<String> rootKeys = root.getKeys(false);
		for (String key : rootKeys) {
			ConfigurationSection headSection = root.getConfigurationSection(key);
			// Check if required keys are missing (id, name, minecraft-url, value, lore)
			if (!(headSection.contains("id") && headSection.contains("name") && headSection.contains("minecraft-url")
					&& headSection.contains("value") && headSection.contains("lore"))) {
				root.set(key, null);
				continue;
			}
			// Remove keys other than required ones here !
			Set<String> variableList = headSection.getKeys(false);
			for (String variable : variableList) {
				if (!("id".equals(variable) || "name".equals(variable) || "minecraft-url".equals(variable)
						|| "value".equals(variable) || "lore".equals(variable))) {
					headSection.set(variable, null);
				}
			}

		}
		saveFile(fileConfig, file);
		return file;
	}

	// validating is file exists
	private boolean isFileExist() {
		File file = new File(plugin.getDataFolder(), "custom-heads.yml");
		return file.exists();
	}

	// copying datas from src/main/resources/custom-heads.yml 
	// to server/plugins/myplugin/custom-heads.yml
	public File loadDefaultFile() {
		File file = new File(plugin.getDataFolder(), "custom-heads.yml");
		YamlConfiguration config = YamlConfiguration
				.loadConfiguration(new InputStreamReader(plugin.getResource("custom-heads.yml")));
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

}