package me.erano.com.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.erano.com.core.examples.ActionBarTask;
import me.erano.com.core.examples.ShowTPSCommand;

public class CorePlugin extends JavaPlugin{

	private String minecraftVersion;
    private NMSHandler nmsHandler;

    @Override
    public void onEnable() {
        getLogger().info("Hello, world! I'll now try to create an NMSHandler for Minecraft " + getMinecraftVersion());
        nmsHandler = createNMSHandler();

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
    private NMSHandler createNMSHandler() {
        String minecraftVersion = getMinecraftVersion();
        String nmsHandlerClassName;

        if (minecraftVersion.equals("1.21.1")) {
            nmsHandlerClassName = "me.erano.com.V1_21_R1.NMSHandlerImpl";
        }
        else if (minecraftVersion.equals("1.20.3") || minecraftVersion.equals("1.20.4")){
            nmsHandlerClassName = "me.erano.com.V1_20_R3.NMSHandlerImpl";
        }
        else if (minecraftVersion.equals("1.20.2")) {
            nmsHandlerClassName = "me.erano.com.V1_20_R2.NMSHandlerImpl";
        } else if (minecraftVersion.equals("1.20") || minecraftVersion.equals("1.20.1")) {
            nmsHandlerClassName = "me.erano.com.V1_20_R1.NMSHandlerImpl";
        } else if(minecraftVersion.equals("1.19") || minecraftVersion.equals("1.19.1") || minecraftVersion.equals("1.19.2")) {
        	nmsHandlerClassName = "me.erano.com.V1_19_R1.NMSHandlerImpl";
        } else if(minecraftVersion.equals("1.19.3")) {
        	nmsHandlerClassName = "me.erano.com.V1_19_R2.NMSHandlerImpl";
        } else if(minecraftVersion.equals("1.19.4")) {
        	nmsHandlerClassName = "me.erano.com.V1_19_R3.NMSHandlerImpl";
        } else if(minecraftVersion.equals("1.18") || minecraftVersion.equals("1.18.1")) {
        	nmsHandlerClassName = "me.erano.com.V1_18_R1.NMSHandlerImpl";
        } else if(minecraftVersion.equals("1.18.2")) {
        	nmsHandlerClassName = "me.erano.com.V1_18_R2.NMSHandlerImpl";
        } else if(minecraftVersion.equals("1.17") || minecraftVersion.equals("1.17.1")) {
        	nmsHandlerClassName = "me.erano.com.V1_17_R1.NMSHandlerImpl";
        }
        
        else {
            throw new RuntimeException("Unsupported Minecraft version: " + minecraftVersion);
        }

        try {
            Class<? extends NMSHandler> clazz = (Class<? extends NMSHandler>) Class.forName(nmsHandlerClassName);
            return clazz.getConstructor().newInstance();
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("Error creating NMSHandler for Minecraft version " + minecraftVersion, exception);
        }
    }
    

    public NMSHandler getNMSHandler() {
        return nmsHandler;
    }
}
