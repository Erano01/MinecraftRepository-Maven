package me.erano.com.particle;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.inventory.ItemStack;

public class MaterialParticle {
	
	private final Particle particle;
	
	public MaterialParticle(Particle particle) throws Exception {
		if (!supportsMaterialParticleValues(particle)) {
			throw new Exception("This particle does not have Material Particle features.");
		}
		this.particle = particle;
	}
	
	//ITEM_CRACK
	public void spawn(
			World world,
			Location location,
			int count,
			ItemStack itemStack
	){
		//use : 
		//ItemStack itemStack = new ItemStack(Material.STONE);
		world.spawnParticle(particle,location,count,itemStack);
		
	}
	//BLOCK_CRACK,BLOCK_DUST,FALLING_DUST
	public void spawn(
			World world,
			Location location,
			int count,
			BlockData blockData
	){
		//use :
		//BlockData blockData = Material.STONE.createBlockData();
		if(!blockData.getMaterial().isBlock()) {
			throw new IllegalArgumentException("Particle has no blockData");
		}
		world.spawnParticle(particle,location,count,blockData);
		
	}
	
	
	protected boolean supportsMaterialParticleValues(Particle particle) {
    	String particleStr = particle.name(); 
        if(MaterialParticleEnum.valueOf(particleStr)!=null) {
        	return true;
        }
        return false;
    }
	
	
	public enum MaterialParticleEnum{
		ITEM_CRACK,
		BLOCK_CRACK,
		BLOCK_DUST,
		FALLING_DUST
	}

}
