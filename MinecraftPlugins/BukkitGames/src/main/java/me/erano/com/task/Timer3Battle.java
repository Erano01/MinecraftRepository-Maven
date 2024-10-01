package me.erano.com.task;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import me.erano.com.Plugin;
import me.erano.com.kit.KitManager;
import net.md_5.bungee.api.ChatColor;

public class Timer3Battle extends BukkitRunnable{
	private static int max_seconds;

    private static int now_seconds;
    
    private static int now_mins;
    
    private final Plugin plugin = Plugin.getInstance();
    
    public Timer3Battle(final int max_seconds) {
		this.max_seconds = max_seconds;
        this.now_seconds = max_seconds;
//        this.plugin=(main)plugin;
        // run task
        World world =plugin.getServer().getWorld("world");
        world.setPVP(true);
        

        this.start();
	}
    public void start() {
    	this.runTaskTimer(this.plugin, 0, 20L);
        for(Player p : getOnlinePlayers()) {
        	KitManager.givePermission(p);
        }
        HandlerList.unregisterAll(plugin.blockBreakEvent);
        HandlerList.unregisterAll(plugin.nofall);
        
    }
	public void stop() {
        this.cancel();
        Timer4DeathmatchPunishment timer = new Timer4DeathmatchPunishment(Plugin.getInstance().getConfig().getInt("timers.deathmatch"));
    }
	public void winner() {
		this.cancel();
		TimerWinner timer = new TimerWinner(Plugin.getInstance().getConfig().getInt("timers.firework"));
	}
	
	private World world= Bukkit.getWorld("world");
    Location l;
    
	@Override
	public void run() {
		if(getOnlineSurvivalPlayers().length==1) {
			Player winner = getOnlineSurvivalPlayers()[0];
			winner.setGameMode(GameMode.CREATIVE);
			plugin.getServer().broadcastMessage(ChatColor.GOLD+"Kazanan "+winner.getName()+"!");
			winner();
		}
		if (this.now_seconds <= 0) {
			//burada arena oluştur.(en son buraya geçici çözüm yaptım ama oyuncular ana merkezi patlatırsa bu iyi olmaz)
			int i =0;
			
			for(Player p : getOnlineSurvivalPlayers()) {
					
				p.teleport(Plugin.locationPairs.get(p));
				p.sendMessage("Arenaya isinlandin!");
				i++;
        	}
			plugin.getServer().broadcastMessage(ChatColor.GOLD+"Final battle has started!");
            this.stop();
            return;
        }
		else if(now_seconds>0 && now_seconds<=300) {
        	if(now_seconds%60==0) {
        		now_mins =now_seconds/60;
        		plugin.getServer().broadcastMessage(ChatColor.GOLD+"Final savasina "+now_mins+" dakika kaldi");
        	}
        	else if(now_seconds<=60 && now_seconds%10==0 && now_seconds!=10 && now_seconds!=0) {
        		plugin.getServer().broadcastMessage(ChatColor.GOLD+"Final savasina "+now_seconds+" saniye kaldi");
        	}
        	else if(now_seconds<=10) {
        		plugin.getServer().broadcastMessage(ChatColor.GOLD+"Final savasina "+ChatColor.RED+now_seconds+ChatColor.GOLD+" saniye kaldi");
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
	public static int getMaxSeconds() {
        return max_seconds;
    }

    public static int getNowSeconds() {
        return now_seconds;
    }

}
