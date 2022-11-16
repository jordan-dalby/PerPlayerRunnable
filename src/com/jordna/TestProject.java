package com.jordna;

import org.bukkit.plugin.java.JavaPlugin;

public class TestProject extends JavaPlugin
{
    
    @Override
    public void onEnable()
    {
	// register events
	getServer().getPluginManager().registerEvents(new InteractEvent(this), this);
    }
    
}