package me.erano.com.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.erano.com.core.performance.TPSHandler;
import me.erano.com.core.performance.TPSHandlerFactoryClassMapper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.erano.com.core.performance.ActionBarTask;
import me.erano.com.core.performance.ShowTPSCommand;

public class CorePlugin extends JavaPlugin{

	private String minecraftVersion;
    private TPSHandler tpsHandler;

    @Override
    public void onEnable() {
        getLogger().info("Hello, world! I'll now try to create an NMSHandler for Minecraft " + getMinecraftVersion());
        tpsHandler = TPSHandlerFactoryClassMapper.getTPSHandlerFactory(getMinecraftVersion()).createTPSHandler();

        // Example: Command to show TPS
        getCommand("showtps").setExecutor(new ShowTPSCommand(this));

        // Example: Task to send TPS to all players as actionbar (only requires NMS on 1.8)
        getServer().getScheduler().runTaskTimer(this, new ActionBarTask(this), 0, 20);
        
    }

    /**
     * Returns the actual running Minecraft version, e.g. 1.20 or 1.16.5
     *
     * @return Minecraft version
     */
    private String getMinecraftVersion() {
        if (minecraftVersion != null) {
            return minecraftVersion;
        } else {
            String bukkitGetVersionOutput = Bukkit.getVersion();
            Matcher matcher = Pattern.compile("\\(MC: (?<version>[\\d]+\\.[\\d]+(\\.[\\d]+)?)\\)").matcher(bukkitGetVersionOutput);
            if (matcher.find()) {
                return minecraftVersion = matcher.group("version");
            } else {
                throw new RuntimeException("Could not determine Minecraft version from Bukkit.getVersion(): " + bukkitGetVersionOutput);
            }
        }
    }
    

    public TPSHandler getTPSHandler() {
        return tpsHandler;
    }
}
