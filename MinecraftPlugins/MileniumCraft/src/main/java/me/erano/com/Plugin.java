package me.erano.com;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.erano.com.economy.PlayerJoinListener;
import me.erano.com.economy.YamlPlayerService;
import me.erano.com.item.CustomEnchantment;
import me.erano.com.item.SkullService;
import me.erano.com.item.TestSkullServiceCommand;
import me.erano.com.kit.KitService;
import me.erano.com.menu.MenuCommand;
import me.erano.com.menu.MenuListener;
import me.erano.com.menu.MenuService;

public class Plugin extends JavaPlugin {

	private SkullService skullService;
	
	private MenuService menuManager;
	
	private KitService kitService;
	
	private YamlPlayerService yamlPlayerService;
	
	public SkullService getSkullService() {
		return skullService;
	}

	public MenuService getMenuService() {
		return menuManager;
	}
	
	public KitService getKitService() {
		return kitService;
	}
	
	public YamlPlayerService getYamlPlayerService() {
		return yamlPlayerService;
	}

	@Override
	public void onEnable() {
		CustomEnchantment.register();
		registerServices();
		registerListeners();
		registerCommands();
	}
	
	@Override
	public void onDisable() {
		
	}

	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new MenuListener(menuManager), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(yamlPlayerService), this);
	}

	private void registerCommands() {
		getCommand("testSkull").setExecutor(new TestSkullServiceCommand(this));
	    getCommand("gui").setExecutor(new MenuCommand(this));
	}
	private void registerServices() {
		yamlPlayerService = new YamlPlayerService(this);
		skullService = new SkullService(this);
		menuManager = new MenuService();
		kitService = new KitService(this);
	}

}
