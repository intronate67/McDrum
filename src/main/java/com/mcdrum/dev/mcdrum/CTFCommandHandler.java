package com.mcdrum.dev.mcdrum;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 6/17/2014.
 */
public class CTFCommandHandler implements CommandExecutor{
    public static final CTFCommandHandler instance = new CTFCommandHandler();
    public static final CTFCommandHandler getInstance(){
        return instance;
    }
    FileConfiguration config;
    public List<String> arenaNames = new ArrayList<String>();
    public HashMap<String, String> inArena = new HashMap<String, String>();
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args){
        if(!(sender instanceof Player)) sender.sendMessage("You cannot do this!");
        Player p = (Player) sender;
        if(args[0].equalsIgnoreCase("join")){
            if(args[1].equals(null) || args.length >= 3){
                p.sendMessage("Command Usage: ");
                p.sendMessage("/ctf join <arenaName>");
                p.sendMessage("--List of Current Arenas");
            }
        }
        if(args[0].equalsIgnoreCase("leave")){
            if(!inArena.containsKey(p.getName())){
                p.sendMessage("You are not in an Arena!");
            }else{
                inArena.remove(p.getName());
                p.sendMessage("You have been removed from the arena!");
            }
        }
        if(args[0].equalsIgnoreCase("admin")){
            if(!p.hasPermission("mcdrum.ctf.admin")){
                p.sendMessage("You do not have permission to do this!");
            }else{
                if(args[1].equalsIgnoreCase("createarena")){
                    if(args[2].equals(null)){
                        p.sendMessage("Commmand usage: ");
                        p.sendMessage("/ctf admin createarena <arenaName>");
                    }else{
                        if(config.contains(args[2])){
                            p.sendMessage("Arena name already exists!");
                        }else {
                            config.set("Games.CTF", args[2]);
                            arenaNames.add(args[2]);
                        }
                    }
                }
                if(args[1].equalsIgnoreCase("setspawn")){
                    if(args[2].equals(null) || args.length >= 4){
                        p.sendMessage("Command usage: ");
                        p.sendMessage("/ctf admin setspawn <arenaName>");
                    }else{
                        if(!config.contains(args[2])){
                            p.sendMessage("Arena ID does not exist!");
                        }else{
                            double x = p.getLocation().getX();
                            double y = p.getLocation().getY();
                            double z = p.getLocation().getZ();
                            String world = p.getWorld().getName();
                            config.set("Games.CTF." + args[2], x + y + z + world);
                        }
                    }
                }
                if(args[1].equalsIgnoreCase("delarena")){
                    if(!config.contains(args[2])){
                        p.sendMessage("Arena does not exist!");
                    }else{
                        config.set("Games.CTF." + args[2], null);
                    }
                }
                if(args[1].equalsIgnoreCase("setteamspawn")){
                    if(args[2].equals(null) || args[3].equals(null) || args.length >= 5){
                        p.sendMessage("Command Usage: ");
                        p.sendMessage("/ctf admin setteamspawn <arenaID> <teamName>");
                    }else if(!config.contains(args[2])){
                        p.sendMessage("Arena ID does not exist!");
                    }else if(!args[3].equalsIgnoreCase("red") || args[3].equalsIgnoreCase("blue")){
                        p.sendMessage("That is not a valid team name!");
                    }else{

                    }
                }
            }
        }
        return true;
    }

}
