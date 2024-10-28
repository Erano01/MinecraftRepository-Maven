package me.erano.com.V1_21_R2.performance;

import me.erano.com.core.performance.TPSHandler;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_21_R2.CraftServer;

public class TPSHandlerImpl implements TPSHandler {
    @Override
    public double[] getTPS() {
        return ((CraftServer) Bukkit.getServer()).getHandle().getServer().recentTps;
    }
}
