package me.erano.com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTFile;
import de.tr7zw.nbtapi.NBTList;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import me.erano.com.command.CoinCommand;
import me.erano.com.command.KitGUICommand;
import me.erano.com.command.OnlinePlayersCommand;
import me.erano.com.command.SetCoinCommand;
import me.erano.com.listener.BlockBreak;
import me.erano.com.listener.ChestEvent;
import me.erano.com.listener.DeathEvent;
import me.erano.com.listener.GlassBreak;
import me.erano.com.listener.JoinEvent;
import me.erano.com.listener.KitGUIMoveItem;
import me.erano.com.listener.MoveEvent;
import me.erano.com.listener.NoFallEvent;
import me.erano.com.listener.skill.AresEvent;
import me.erano.com.listener.skill.BoxorEvent;
import me.erano.com.listener.skill.CannibalEvent;
import me.erano.com.listener.skill.HadesEvent;
import me.erano.com.listener.skill.KingArcherEvent;
import me.erano.com.listener.skill.TarzanEvent;
import me.erano.com.listener.skill.VampireEvent;
import me.erano.com.task.Timer1GameStarter;
import me.erano.com.util.Database;

public class Plugin extends JavaPlugin{

	private File customConfigFile;
	private FileConfiguration customConfig;

	public boolean isGamePlaying = false;

	// define event references static because we want to access them

	public static ArrayList<Location> spawnLocations = new ArrayList<Location>();

	public static HashMap<Player, Location> locationPairs = new HashMap<>();

	public static ArrayList<Listener> kitEvents = new ArrayList<Listener>();

	public static NoFallEvent nofall = new NoFallEvent();
	public static BlockBreak blockBreakEvent = new BlockBreak();
	public static DeathEvent playerDeathEvent = new DeathEvent();
	public static JoinEvent playerJoinEvent = new JoinEvent();
	public static ChestEvent chestEvent = new ChestEvent();
	public static GlassBreak glassBreakEvent = new GlassBreak();
	public static MoveEvent moveEvent = new MoveEvent();

	private static Plugin plugin;

	public static Plugin getInstance() {
		return plugin;
	}

	public Plugin() {
		plugin = this;
	}

	@Override
	public void onEnable() {

		System.out.println("Plugin aktif");
		createCustomConfig();
		Database.createTableIfNotExists();
		kitEvents.add(new AresEvent());
		kitEvents.add(new BoxorEvent());
		kitEvents.add(new CannibalEvent());
		kitEvents.add(new HadesEvent());
		kitEvents.add(new KingArcherEvent());
		kitEvents.add(new TarzanEvent());
		kitEvents.add(new VampireEvent());

		for (Listener l : kitEvents) {
			getServer().getPluginManager().registerEvents(l, this);
		}
		getServer().getPluginManager().registerEvents(nofall, this);
		getServer().getPluginManager().registerEvents(blockBreakEvent, this);
		getServer().getPluginManager().registerEvents(playerDeathEvent, this);
		getServer().getPluginManager().registerEvents(new KitGUIMoveItem(), this);
		getServer().getPluginManager().registerEvents(playerJoinEvent, this);
		getServer().getPluginManager().registerEvents(chestEvent, this);
		getServer().getPluginManager().registerEvents(moveEvent, this);

		getCommand("Kit").setExecutor(new KitGUICommand());
		getCommand("Online").setExecutor(new OnlinePlayersCommand());
		getCommand("setcoin").setExecutor(new SetCoinCommand());
		getCommand("coin").setExecutor(new CoinCommand());
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		startGame();
		// Plugin yüklenirken yapılacaklar
		// file alıp nbtCompound döndüren yapı

		try {

			NBTFile file = new NBTFile(new File("world/level.dat"));

			NBTCompound data = file.getCompound("Data");

			if (data == null) {
				getLogger().warning("Data compound bulunamadı!");
				return;
			}
			NBTCompound spawns = data.getCompound("Spawns");// içinde liste bulunan bir compound

			if (spawns == null) {
				getLogger().warning("Spawn noktaları bulunamadı!");
				return;
			}
			NBTList<ReadWriteNBT> spawnPoints = spawns.getCompoundList("SpawnPoints");

			if (spawnPoints == null) {
				getLogger().warning("Spawn noktaları bulunamadı!");
				return;
			}
			for (ReadWriteNBT spawn : spawnPoints) {
				double x = spawn.getDouble("x");
				double y = spawn.getDouble("y");
				double z = spawn.getDouble("z");
				spawnLocations.add(new Location(getServer().getWorld("World"), x, y, z));

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collections.shuffle(spawnLocations);

		World world = getServer().getWorld("world");
		world.setSpawnFlags(false, false);
		// delete mobs -> animal, zombie
		for (Entity entity : Bukkit.getWorld("world").getEntities()) {
			if (!(entity instanceof Player) && !(entity instanceof Item)) {
				entity.remove();
			}
		}

	}

	public void startGame() {

		Timer1GameStarter timer = new Timer1GameStarter(this.getConfig().getInt("timers.gamestarter"));

	}

	@Override
	public void onDisable() {
		for (Listener l : kitEvents) {
			HandlerList.unregisterAll(l);
		}

	}

	public FileConfiguration getCustomConfig() {
		return this.customConfig;
	}

	private void createCustomConfig() {
		customConfigFile = new File(getDataFolder(), "config.yml");
		if (!customConfigFile.exists()) {
			customConfigFile.getParentFile().mkdirs();
			saveResource("config.yml", false);
		}

		customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	}

	public Player[] getOnlinePlayers() {
		ArrayList<Player> online = new ArrayList<>();
		for (Player p : getServer().getOnlinePlayers()) {
			online.add(p);
		}
		return (Player[]) online.toArray(new Player[0]);
	}
}
