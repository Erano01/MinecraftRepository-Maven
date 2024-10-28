package me.erano.com.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.erano.com.core.performance.TPSHandler;
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
        tpsHandler = createNMSHandler();

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

    /**
     *
     * @return NMSHandlerImpl appropriate to the version of the server.
     */
    @SuppressWarnings("unchecked")
    private TPSHandler createNMSHandler() {
        String minecraftVersion = getMinecraftVersion();
        String tpsHandlerClassName;

        if (minecraftVersion.equals("1.21.2") ||minecraftVersion.equals("1.21.3")) {
            tpsHandlerClassName = "me.erano.com.V1_21_R2.performance.TPSHandlerImpl";
        }
        else if (minecraftVersion.equals("1.21.1")){
            tpsHandlerClassName = "me.erano.com.V1_21_R1.performance.TPSHandlerImpl";
        }
        else if (minecraftVersion.equals("1.20.3") || minecraftVersion.equals("1.20.4")){
            tpsHandlerClassName = "me.erano.com.V1_20_R3.performance.TPSHandlerImpl";
        }
        else if (minecraftVersion.equals("1.20.2")) {
            tpsHandlerClassName = "me.erano.com.V1_20_R2.performance.TPSHandlerImpl";
        } else if (minecraftVersion.equals("1.20") || minecraftVersion.equals("1.20.1")) {
            tpsHandlerClassName = "me.erano.com.V1_20_R1.performance.TPSHandlerImpl";
        } else if(minecraftVersion.equals("1.19") || minecraftVersion.equals("1.19.1") || minecraftVersion.equals("1.19.2")) {
            tpsHandlerClassName = "me.erano.com.V1_19_R1.performance.TPSHandlerImpl";
        } else if(minecraftVersion.equals("1.19.3")) {
            tpsHandlerClassName = "me.erano.com.V1_19_R2.performance.TPSHandlerImpl";
        } else if(minecraftVersion.equals("1.19.4")) {
            tpsHandlerClassName = "me.erano.com.V1_19_R3.performance.TPSHandlerImpl";
        } else if(minecraftVersion.equals("1.18") || minecraftVersion.equals("1.18.1")) {
            tpsHandlerClassName = "me.erano.com.V1_18_R1.performance.TPSHandlerImpl";
        } else if(minecraftVersion.equals("1.18.2")) {
            tpsHandlerClassName = "me.erano.com.V1_18_R2.performance.TPSHandlerImpl";
        } else if(minecraftVersion.equals("1.17") || minecraftVersion.equals("1.17.1")) {
            tpsHandlerClassName = "me.erano.com.V1_17_R1.performance.TPSHandlerImpl";
        }
        
        else {
            throw new RuntimeException("Unsupported Minecraft version: " + minecraftVersion);
        }

        try {
            Class<? extends TPSHandler> clazz = (Class<? extends TPSHandler>) Class.forName(tpsHandlerClassName);
            return clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("Error creating TPSHandler for Minecraft version " + minecraftVersion, exception);
        }
    }
    

    public TPSHandler getTPSHandler() {
        return tpsHandler;
    }
}
