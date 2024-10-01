package me.erano.com.listener.skill;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class TarzanEvent implements Listener{
	@EventHandler
	public void onWalk(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if(player.hasPermission("Bukkitgames.tarzan")&&!player.isOp()) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,1000,1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,1000,3));
		}
	}
	public ArrayList<Player> thiefList = new ArrayList<Player>();
	//1.slotta olunca cubuk calıyor sadece
	
	Random rand =new Random();
	@EventHandler
	public void stealItem(EntityDamageByEntityEvent e) {
		Entity entity1 = e.getEntity();
		Entity entity2 = e.getDamager();
		if(entity1 instanceof Player && entity2 instanceof Player) {
			Player victim= (Player)entity1;
			Player attacker= (Player)entity2;
			if(attacker.hasPermission("Bukkitgames.tarzan")&&!attacker.isOp()) {
				if(attacker.getItemInHand().getType() == Material.STICK && victim.getItemInHand()!=null) {
				int variable = rand.nextInt(100);
				if(variable>0 && variable<6) {
					//bu �zellik �ok op item �almay� cooldowna sok yada �alma �ans�n� azalt
					
					attacker.getInventory().clear(attacker.getInventory().getHeldItemSlot());
					attacker.getInventory().addItem(victim.getItemInHand());
					victim.getInventory().clear(victim.getInventory().getHeldItemSlot());
					victim.sendMessage(ChatColor.RED+attacker.getName()+ChatColor.GREEN+" Tarzan� elindekini ald� dikkat et !");
					}
				}
			}
			
		}
	}
}
