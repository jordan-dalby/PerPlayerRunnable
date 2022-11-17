package com.jordna;

import org.bukkit.plugin.java.JavaPlugin;

public class TestProject extends JavaPlugin
{
    
    private GlobalRunnable globalRunnable;
    
    @Override
    public void onEnable()
    {
	globalRunnable = new GlobalRunnable();
	globalRunnable.runTaskTimer(this, 0, 20);
	
	// register events
	getServer().getPluginManager().registerEvents(new InteractEvent(this), this);
	getServer().getPluginManager().registerEvents(new RespawnEvent(this), this);
    }
    
    public GlobalRunnable getRunnable()
    {
	return globalRunnable;
    }
}