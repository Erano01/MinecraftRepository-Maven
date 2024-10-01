package me.erano.com.task;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import me.erano.com.Plugin;
import net.md_5.bungee.api.ChatColor;

public final class Timer1GameStarter extends BukkitRunnable {

    private static int max_seconds;

    private static int now_seconds;
    
    private static int now_mins;
    
    private Integer min_player = Plugin.getInstance().getConfig().getInt("minplayertostart");
    
    private final Plugin plugin = Plugin.getInstance();
    
//    public static GameTimer1 runTimer(final @NotNull JavaPlugin plugin, final int max_seconds) {
//        return new GameTimer1(plugin, max_seconds);
//    }
	
    public Timer1GameStarter(final int max_seconds) {
        this.max_seconds = max_seconds;
        this.now_seconds = max_seconds;
//        plugin=(main) plugin_; 
        World world =plugin.getServer().getWorld("world");
        world.setPVP(false);
        
        start();
    }
    public void start() {
    	Plugin.getInstance().isGamePlaying = false;
        this.runTaskTimer(this.plugin, 0, 20L);
    }
	public void stop() {
        this.cancel();
        
        HandlerList.unregisterAll(Plugin.moveEvent);
        plugin.isGamePlaying = true;
        Timer2Invincible timer = new Timer2Invincible(Plugin.getInstance().getConfig().getInt("timers.invincible"));
        timer.runTaskTimer(plugin, 0, 20L);
        
        
    }
    
    private World world= Bukkit.getWorld("world");
    
    Location l;
	@Override
    public void run() {
		
        if (this.now_seconds <= 0 && getOnlinePlayers().length>=min_player) {
        	for(Player p : getOnlinePlayers()) {
        		p.sendMessage("Hazır ol");
        		
        		
        		world.playSound(l, Sound.BLOCK_ANVIL_PLACE, 2, 1);
        	}
        	plugin.getServer().broadcastMessage(ChatColor.GOLD+"Kendi rotani olustur !");
        	HandlerList.unregisterAll(Plugin.chestEvent);
        	this.stop();
            return;
        }
        else if(this.now_seconds<=0 && getOnlinePlayers().length<=min_player){
        	//timer reset scope
        	now_seconds = max_seconds;
        	System.out.println("Zamanlayici yeniden baslatiliyor");
        	plugin.getServer().broadcastMessage(ChatColor.GOLD+"Oyunun baslayabilmesi icin minimum oyuncu sayisi :"+ChatColor.RED+min_player);
        }
        else if(now_seconds>0) {
        	if(now_seconds%60==0) {
        		now_mins =now_seconds/60;
        		plugin.getServer().broadcastMessage(ChatColor.GOLD+"Oyunun baslamasina "+now_mins+" dakika kaldi");
        	}
        	else if(now_seconds<=60 && now_seconds%10==0 && now_seconds!=10 && now_seconds!=0) {
        		plugin.getServer().broadcastMessage(ChatColor.GOLD+"Oyunun baslamasina "+now_seconds+" saniye kaldi");
        	}
        	else if(now_seconds<=10) {
        		plugin.getServer().broadcastMessage(ChatColor.GOLD+"Basliyor.. "+now_seconds);
        		for(Player p : plugin.getServer().getOnlinePlayers()) {
        			l = p.getLocation();
        			p.playSound(l, Sound.ENTITY_SNOWBALL_THROW, 1, 4);
        		}
        	}
        }
        
        
        now_seconds--;
    }
    public Player[] getOnlinePlayers() {
    	ArrayList<Player> online = new ArrayList<>();
		for(Player p : plugin.getServer().getOnlinePlayers()) {
			online.add(p);
		}
		return (Player[]) online.toArray(new Player[0]);
	}

    @Override
    public synchronized void cancel() throws IllegalStateException {
        if (!this.isCancelled())
            super.cancel();
    }

    public static int getMaxSeconds() {
        return max_seconds;
    }

    public static int getNowSeconds() {
        return now_seconds;
    }
}