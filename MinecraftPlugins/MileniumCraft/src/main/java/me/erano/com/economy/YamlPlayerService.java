package me.erano.com.economy;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.UUID;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class YamlPlayerService {

	private final JavaPlugin plugin;
	private File file;

	public YamlPlayerService(JavaPlugin plugin) {
		this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), "player-data.yml");
        if (!isFileExist()) {
            this.file = loadDefaultFile();
        }
	}
	public Integer getPlayerCoin(UUID uuid) {
		FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
		ConfigurationSection playerSection = fileConfig
				.getConfigurationSection("player-data").getConfigurationSection(uuid.toString());
		return playerSection.getInt(uuid.toString());
	}
	public void updatePlayerCoin(UUID uuid, int amount) {
		FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
		ConfigurationSection playerSection = fileConfig
				.getConfigurationSection("player-data").getConfigurationSection(uuid.toString());
		playerSection.set(uuid.toString(), amount);
	}
	public boolean registerPlayer(Player player) {
		if(isPlayerRegistered(player)) {
			return false;
		}
		FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
		ConfigurationSection playerData = fileConfig.getConfigurationSection("player-data");
		ConfigurationSection playerSection = playerData.createSection(player.getUniqueId().toString());
		playerSection.set("coin", 0);
		saveFile(fileConfig, file);
		return true;
	}
	private boolean isPlayerRegistered(Player player) {
		FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
		ConfigurationSection playerData = fileConfig.getConfigurationSection("player-data");
		Set<String> playerDataSet = playerData.getKeys(false);
		for (String string : playerDataSet) {
			if(string.equals(player.getUniqueId().toString())) {
				return true;
			}
		}
		return false;
	}

	private boolean isFileExist() {
		File file = new File(plugin.getDataFolder(), "player-data.yml");
		return file.exists();
	}

	public File loadDefaultFile() {
		File file = new File(plugin.getDataFolder(), "player-data.yml");
		YamlConfiguration config = YamlConfiguration
				.loadConfiguration(new InputStreamReader(plugin.getResource("player-data.yml")));
		saveFile(config, file);
		return file;
	}

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
