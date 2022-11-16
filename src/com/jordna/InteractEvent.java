package com.jordna;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class InteractEvent implements Listener
{

    // vars
    private TestProject instance;
    private List<UUID> cooldowns = new ArrayList<UUID>();
    
    // constructor
    public InteractEvent(TestProject _instance)
    {
	instance = _instance;
    }
    
    // event
    @EventHandler
    public void onEvent(PlayerInteractEvent event)
    {
	Player player = event.getPlayer();
	
	// doesn't concern a player, we don't care
	if (player == null)
	    return;
	
	// check if the cooldowns contains the players ID
	if (cooldowns.contains(event.getPlayer().getUniqueId()))    
	{
	    // cancel the event and return
	    event.setCancelled(true);
	    return;
	}
	
	// add the player to the cooldowns list
	cooldowns.add(player.getUniqueId());
	
	// start a new runnable
        new BukkitRunnable() {
            
            // how many times should this runnable run?
    	    int remainingCooldown = 10;
    	    
            @Override
            public void run() {
        	// reduce the cooldown time
        	remainingCooldown--;
        	// if the cooldown is less than or equal to 0
        	if (remainingCooldown <= 0)
        	{
        	    // cooldown is done
        	    // remove player from the cooldowns list
        	    cooldowns.remove(player.getUniqueId());
        	    // send them a message
          	    player.sendMessage(ChatColor.GREEN + "Cooldown reset!");
          	    // increase the players level
          	    player.setLevel(player.getLevel() + 1);
          	    // cancel this runnable
          	    cancel();
        	}
        	else
        	{
        	    // tell the player how long they have left of the cooldown
        	    player.sendMessage(ChatColor.RED + "Cooldown " + remainingCooldown + " seconds");
        	}
    
                /* I don't understand what this was supposed to do, we can keep track of the timer based on the remaining cooldown
                 * we really don't need to care what level the player is
                 * If you wish to reinstate this, why are we even using a remainingCooldown counter?
                if (player.getLevel() == 3) {
                    cooldowns.remove(player.getUniqueId());
                    cancel();
                }
                */
        	
            }
        }.runTaskTimer(instance, 0, 20);
    }
    
}