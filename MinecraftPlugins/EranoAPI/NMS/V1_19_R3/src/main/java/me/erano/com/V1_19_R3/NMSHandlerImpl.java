package me.erano.com.V1_19_R3;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_19_R3.CraftServer;

import me.erano.com.core.NMSHandler;

public class NMSHandlerImpl implements NMSHandler{
	@Override
    public double[] getTPS() {
        return ((CraftServer) Bukkit.getServer()).getHandle().getServer().recentTps;
    }
}
