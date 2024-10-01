package me.erano.com.task;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.erano.com.Plugin;
import me.erano.com.kit.Default;
import me.erano.com.kit.KitManager;
import net.md_5.bungee.api.ChatColor;

public final class Timer2Invincible extends BukkitRunnable {
	
	 	private static int max_seconds;

	    private static int now_seconds;
	    
	    private static int now_mins;
	    
	    private final Plugin plugin=Plugin.getInstance();
	    
	    Player winner;
	    
	public Timer2Invincible(final int max_seconds) {
		this.max_seconds = max_seconds;
        this.now_seconds = max_seconds;
//        this.plugin=(main)plugin;
        World world =plugin.getServer().getWorld("world");
        world.setPVP(false);
        // run task
        this.start();
	}
	public void start() {
        this.runTaskTimer(this.plugin, 0, 20L);
        for(Player p : getOnlinePlayers()) {
        	//eger oyuncu /kit yapmamışsa default ver
        	if(KitManager.getPlayers().get(p)==null) {
        		KitManager.setPermission(p, new Default());
        		KitManager.givePermission(p);
        	}
        	KitManager.equipArmor(p);
        	KitManager.equipPlayerKit(p);
        }
		
    }
	public void stop() {
        this.cancel();
        if(winner == null) {
        	Timer3Battle timer = new Timer3Battle(Plugin.getInstance().getConfig().getInt("timers.battle"));
        }else {
        	TimerWinner timer = new TimerWinner(Plugin.getInstance().getConfig().getInt("timers.firework"));
        }
        

    }
	private World world= Bukkit.getWorld("world");
    Location l;
	@Override
	public void run() {
		
		if(getOnlineSurvivalPlayers().length==1) {
			winner = getOnlineSurvivalPlayers()[0];
			winner.setGameMode(GameMode.CREATIVE);
			plugin.getServer().broadcastMessage(ChatColor.GOLD+"Kazanan "+winner.getName()+"!");
			stop();
		}
		if (this.now_seconds <= 0) {
			plugin.getServer().broadcastMessage(ChatColor.GOLD+"Olumsuzluk sona erdi!");
            //startgame
            this.stop();
            return;
        }
		else if(now_seconds>0) {
        	if(now_seconds%60==0) {
        		now_mins =now_seconds/60;
        		plugin.getServer().broadcastMessage(ChatColor.GOLD+"Olumsuzlugun kapanmasına "+now_mins+" dakika kaldi");
        	}
        	else if(now_seconds<=60 && now_seconds%10==0 && now_seconds!=10 && now_seconds!=0) {
        		plugin.getServer().broadcastMessage(ChatColor.GOLD+"Olumsuzlugun kapanmasına "+now_seconds+" saniye kaldi");
        	}
        	else if(now_seconds<=10) {
        		plugin.getServer().broadcastMessage(ChatColor.GOLD+"Olumsuzlugun kapanmasına "+ChatColor.RED+now_seconds+ChatColor.GOLD+" saniye kaldi");
        		for(Player p : plugin.getServer().getOnlinePlayers()) {
        			l = p.getLocation();
        			p.playSound(l, Sound.ENTITY_SNOWBALL_THROW, 1, 4);
        		}
        	}
        }
		now_seconds--;
		
	}
	@Override
    public synchronized void cancel() throws IllegalStateException {
        if (!this.isCancelled())
            super.cancel();
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
}
