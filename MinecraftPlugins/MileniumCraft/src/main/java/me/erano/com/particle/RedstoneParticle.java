package me.erano.com.particle;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.World;

public class RedstoneParticle implements ColoredParticle{
	
	protected final Particle particle = Particle.REDSTONE;
	
	//T data from World.spawnParticle method
	protected final DustOptions dustOptions;
	
	public RedstoneParticle(int red, int green, int blue, float particleSize) {
		//Red-Green-Blue -> RGB
		this.dustOptions = new DustOptions(Color.fromRGB(red, green, blue), particleSize);
	}
	public void spawn(
			World world,
			Location location,
			int count
	){
		world.spawnParticle(particle, location,count,this.dustOptions);
	}
	public Particle getParticle() {
		return particle;
	}
	public DustOptions getDustOptions() {
		return dustOptions;
	}
	

}
