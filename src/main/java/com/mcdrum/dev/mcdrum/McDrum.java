package com.mcdrum.dev.mcdrum;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Admin on 6/15/2014.
 */
public class McDrum extends JavaPlugin{
    FileConfiguration config;
    @Override
    public void onEnable(){
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
            config = getConfig();
            saveDefaultConfig();
        }

    }
    @Override
    public void onDisable(){
        String arenaNames = CTFCommandHandler.getInstance().arenaNames.toString();
        config.set("Games.CTF.arenaNames", arenaNames);
    }

}
