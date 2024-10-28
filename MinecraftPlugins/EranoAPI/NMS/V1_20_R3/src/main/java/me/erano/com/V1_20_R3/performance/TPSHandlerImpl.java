package me.erano.com.V1_20_R3.performance;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_20_R3.CraftServer;

import me.erano.com.core.performance.TPSHandler;

public class TPSHandlerImpl implements TPSHandler {
    @Override
    public double[] getTPS() {
        return ((CraftServer) Bukkit.getServer()).getHandle().getServer().recentTps;
    }
}
