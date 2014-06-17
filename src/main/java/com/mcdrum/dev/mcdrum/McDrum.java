package com.mcdrum.dev.mcdrum;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Admin on 6/15/2014.
 */
public class McDrum extends JavaPlugin{
    FileConfiguration config;
    private McDrum instance = new McDrum();

    @Override
    public void onEnable(){
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
            config = getConfig();
            saveDefaultConfig();
        }
        getCommand("ctf").setExecutor(new CTFCommandHandler());
    }
    @Override
    public void onDisable(){

    }

}
