package com.jordna;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEvent implements Listener
{

    // vars
    private TestProject instance;
    
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
	
	// TODO: do creeper egg checks here
	
	if (!instance.getRunnable().hasPlayer(player.getUniqueId()))
	{
	    instance.getRunnable().addNewPlayer(player.getUniqueId());
	    player.setLevel(instance.getRunnable().MAX);
	}

	// try to add a cooldown for the player, if it fails then we have no active cooldown slots, cancel the event
	if (!instance.getRunnable().addNewCooldown(player.getUniqueId()))
	{
	    event.setCancelled(true);
	    return;
	}
	
	player.setLevel(instance.getRunnable().getInactiveCooldowns(player.getUniqueId()));
	
	// TODO: explosion code goes here
    }
    
}