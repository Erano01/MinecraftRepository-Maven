package me.erano.com.task;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.erano.com.Plugin;
import net.md_5.bungee.api.ChatColor;

public class Timer4DeathmatchPunishment extends BukkitRunnable{

    private static int now_seconds;
    
    private static int now_mins;
    
    private static Player winner;
    
    private final Plugin plugin = Plugin.getInstance();
    
    public Timer4DeathmatchPunishment(int max_seconds) {
        this.now_seconds = max_seconds;
        World world =plugin.getServer().getWorld("world");
        world.setPVP(true);
        this.start();
	}
    public void start() {
        this.runTaskTimer(this.plugin, 0, 20L);

    }
	public void stop() {
		
		TimerWinner timer = new TimerWinner(Plugin.getInstance().getConfig().getInt("timers.firework"));
        this.cancel();
    }

	
	private Random rand = new Random();
	@Override
	public void run() {
		//ArrayList<Player> survivors= getAlivePlayers();
		if(getOnlineSurvivalPlayers().length==1) {
			winner = getOnlineSurvivalPlayers()[0];
			winner.setGameMode(GameMode.CREATIVE);
			plugin.getServer().broadcastMessage(ChatColor.GOLD+"Kazanan "+winner.getName()+"!");
			stop();
		}
		if(now_seconds<60) {
			if(now_seconds%10==0) {
				int random = rand.nextInt(getOnlineSurvivalPlayers().length);
				Player p = getOnlineSurvivalPlayers()[random];
				Location l = p.getLocation();
				World world =plugin.getServer().getWorld("world");
				world.strikeLightning(l);
			}
		}
		
		
		now_seconds--;
	}
	public ArrayList<Player> getAlivePlayers(){
		ArrayList<Player> tmp = new ArrayList<>();
		for(Player alive :getOnlinePlayers()) {
			if(alive.getGameMode().equals(GameMode.SURVIVAL)) {
				tmp.add(alive);
			}
		}
		
		return tmp;
	}
	@Override
    public synchronized void cancel() throws IllegalStateException {
        if (!this.isCancelled())
            super.cancel();
    }//dışardan cancel için
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

    public static int getNowSeconds() {
        return now_seconds;
    }
	

}
