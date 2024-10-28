package me.erano.com.V1_21_R2;

import me.erano.com.core.NMSHandler;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_21_R2.CraftServer;

public class NMSHandlerImpl implements NMSHandler {
    @Override
    public double[] getTPS() {
        return ((CraftServer) Bukkit.getServer()).getHandle().getServer().recentTps;
    }
}
