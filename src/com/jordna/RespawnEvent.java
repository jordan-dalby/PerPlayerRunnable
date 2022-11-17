package com.jordna;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class RespawnEvent implements Listener
{

    private TestProject instance;
    
    public RespawnEvent(TestProject _instance)
    {
	instance = _instance;
    }
    
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event)
    {
	if (instance.getRunnable().hasPlayer(event.getPlayer().getUniqueId()))
	{
	    event.getPlayer().setLevel(instance.getRunnable().getInactiveCooldowns(event.getPlayer().getUniqueId()));
	}
    }
    
}
