package me.erano.com.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class NoteParticle implements ColoredParticle{

	//sadece 24 renk değeri-> offSetX ile sağlanır
	
	private final Particle particle = Particle.NOTE;
	
	private final int count = 0;
	
	private final int extra = 0;
	
	//default calor
	private double offSetX = 1/24d;
	
	private final int offSetY =0;
	
	private final int offSetZ =0;
	
	
	public void spawn(
			World world,
			Location location,
			double offSetX
	){
		this.offSetX = offSetX/24d;
		world.spawnParticle(particle,location,count,this.offSetX,offSetY,offSetZ,extra);
	}


	public double getOffSetX() {
		return offSetX;
	}


	public void setOffSetX(double offSetX) {
		this.offSetX = offSetX;
	}
	
	
}
