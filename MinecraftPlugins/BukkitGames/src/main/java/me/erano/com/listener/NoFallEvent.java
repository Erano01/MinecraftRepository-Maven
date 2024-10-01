package me.erano.com.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class NoFallEvent implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onFallDamage(EntityDamageEvent event){
        if(event.getEntity() instanceof Player && event.getCause() == DamageCause.FALL)
             event.setCancelled(true);
    }
}
