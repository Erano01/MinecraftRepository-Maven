package me.erano.com.task;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import me.erano.com.Plugin;
import me.erano.com.util.Database;
import net.md_5.bungee.api.ChatColor;

public class TimerWinner extends BukkitRunnable{

	private static int max_seconds;

    private static int now_seconds;
    
    private static int now_mins;
    
    private final Plugin plugin = Plugin.getInstance();
    
    public TimerWinner(final int max_seconds) {
		this.max_seconds = max_seconds;
        this.now_seconds = max_seconds;
//        this.plugin=(main)plugin;
        // run task
        World world =plugin.getServer().getWorld("world");
        world.setPVP(true);
        

        this.start();
	}
	@Override
	public void run() {
		if(getWinner() == null) {
			plugin.getServer().broadcastMessage(ChatColor.GOLD+"Kazanan Oyundan Cıktı Oyun "+now_seconds+ " Saniye Sonra Sonlandırılıcak..");
			
		}else {
			Location l = getWinner().getLocation();
			World world = getWinner().getWorld();
			Firework firework = (Firework) world.spawn(l, Firework.class);
            FireworkMeta fireworkMeta = firework.getFireworkMeta();
            fireworkMeta.addEffect(FireworkEffect.builder()
                .withColor(Color.RED)
                .with(FireworkEffect.Type.STAR)
                .build());
            fireworkMeta.setPower(1);
            firework.setFireworkMeta(fireworkMeta);
			
		}
		if(now_seconds ==9) {
			plugin.getServer().broadcastMessage(ChatColor.RED+getWinner().getName()+" oyunu kazandığı için 20 coin kazandı");
		}
		if(now_seconds==5) {
			if(Plugin.getInstance().getConfig().getBoolean("hooks.bungeecord")) {
				//send players to lobby

				for(Player p : plugin.getServer().getOnlinePlayers()) {
					if(p.getGameMode().equals(GameMode.CREATIVE)) {
						p.setGameMode(GameMode.SURVIVAL);
					}
					if(p.getGameMode().equals(GameMode.SPECTATOR)) {
						p.setGameMode(GameMode.SURVIVAL);
					}
					sendPlayer(p,Plugin.getInstance().getConfig().getString("hooks.sendPlayerToServer"));
				}
				
			}
			this.stop();
		}
		now_seconds--;
	}
	public void start() {
    	this.runTaskTimer(this.plugin, 0, 20L);

    }
	public void stop() {
		Database.setCoin(getWinner().getUniqueId(), Database.getCoin(getWinner().getUniqueId())+20);
		
		this.cancel();
		plugin.getServer().shutdown();
        
    }
	public void sendPlayer(Player player,String serverName) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
		try {
			dataOutputStream.writeUTF("Connect");
			dataOutputStream.writeUTF(serverName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		player.sendPluginMessage(Plugin.getInstance(), "BungeeCord", byteArrayOutputStream.toByteArray());
	}
	
	@Override
    public synchronized void cancel() throws IllegalStateException {
        if (!this.isCancelled())
            super.cancel();
    }
	public Player getWinner() {
		for(Player p : getOnlinePlayers()) {
			if(p.getGameMode().equals(GameMode.CREATIVE)) {
				
				return p;
			}
			
		}
		return null;
	}
	
	public Player[] getOnlinePlayers() {
    	ArrayList<Player> online = new ArrayList<>();
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			online.add(p);
		}
		return (Player[]) online.toArray(new Player[0]);
	}
	public Player[] getOnlineSurvivalPlayers() {
		ArrayList<Player> survivor = new ArrayList<>();
		for(Player p : getOnlinePlayers()) {
			if(p.getGameMode().equals(GameMode.SURVIVAL)) {
				survivor.add(p);
			}
		}
		return (Player[]) survivor.toArray(new Player[0]);
	}

	public static int getMaxSeconds() {
        return max_seconds;
    }

    public static int getNowSeconds() {
        return now_seconds;
    }

}
