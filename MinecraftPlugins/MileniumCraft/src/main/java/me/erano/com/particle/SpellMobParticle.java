package me.erano.com.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

public class SpellMobParticle implements ColoredParticle{
	
	protected final Particle particle;
	
	protected double red;

	protected double green;

	protected double blue;
	
	protected final int count = 0;
	
	protected final int extra = 1;
	
	public SpellMobParticle(Particle particle,int red, int green,int blue) throws Exception{
		if(!supportsSpellMobValues(particle)) {
			throw new Exception("This particle does not support spellmob features");
		}
		if(!(red/255D>=0 && red/255D<=255)) {
			throw new Exception("Red value need value around 0-255");
		}
		if(!(green/255D>=0 && green/255D<=255)) {
			throw new Exception("Green value need value around 0-255");
		}
		if(!(blue/255D>=0 && blue/255D<=255)) {
			throw new Exception("Blue value need value around 0-255");
		}
		this.red=red;
		this.green=green;
		this.blue=blue;
		this.particle = particle;
	}
	public void spawn(World world,Location location){
		
		world.spawnParticle(particle,location,count,red,green,blue,extra);
	}
	protected boolean supportsSpellMobValues(Particle particle) {
    	String particleStr = particle.name(); 
        if(SpellMobParticleEnum.valueOf(particleStr)!=null) {
        	return true;
        }
        return false;
    }
	
	public enum SpellMobParticleEnum{
		SPELL_MOB,
		SPELL_MOB_AMBIENT
	};

}
