package me.erano.com.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class DirectionalParticle{
	
	protected Particle particle;
	
	protected final int count = 0;

	public DirectionalParticle(Particle particle) throws Exception{
		if(!supportsDirectionalValues(particle)) {
			throw new Exception("This particle does not support directional features");
		}
		this.particle = particle;
	}
	//double speed is extra data
	public void spawn(
			World world,
			Particle particle,
			Location location,
			int offsetX, int offsetY, int offsetZ, double speed
	){
		world.spawnParticle(particle,location,count,offsetX,offsetY,offsetZ,speed);
	}
	
	protected boolean supportsDirectionalValues(Particle particle) {
    	String particleStr = particle.name(); 
        if(DirectionalParticleEnum.valueOf(particleStr)!=null) {
        	return true;
        }
        return false;
    }
	
	public Particle getParticle() {
		return particle;
	}

	public void setParticle(Particle particle)throws Exception{
		if(!supportsDirectionalValues(particle)) {
			throw new Exception("This particle does not support directional features");
		}
		this.particle = particle;
	}

	public int getCount() {
		return count;
	}
	public enum DirectionalParticleEnum{
		BUBBLE_COLUMN_UP,
		BUBBLE_POP,
		CAMPFIRE_COZY_SMOKE,
		CAMPFIRE_SIGNAL_SMOKE,
		CLOUD,
		CRIT,
		CRIT_MAGIC,
		DAMAGE_INDICATOR,
		DRAGON_BREATH,
		ELECTRIC_SPARK,
		ENCHANTMENT_TABLE,
		END_ROD,
		EXPLOSION_NORMAL,
		FIREWORKS_SPARK,
		FLAME,
		NAUTILUS,
		PORTAL,
		REVERSE_PORTAL,
		SCRAPE,
		SCULK_CHARGE,
		SCULK_CHARGE_POP,
		SCULK_SOUL,
		SMALL_FLAME,
		SMOKE_LARGE,
		SMOKE_NORMAL,
		SOUL,
		SOUL_FIRE_FLAME,
		SPIT,
		SQUID_INK,
		TOTEM,
		WATER_BUBBLE,
		WATER_WAKE,
		WAX_OFF,
		WAX_ON;
		
	}
    
	
}
