package com.carlgo11.CommandBouncer;

import com.carlgo11.CommandBouncer.Commands.*;
import com.carlgo11.CommandBouncer.updater.Updater;
import com.carlgo11.CommandBouncer.player.CommandListener;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBouncer extends JavaPlugin {

    public static CommandBouncer plugin;

    public void onEnable() {
        this.reloadConfig();
        this.getLogger().info(getDescription().getName() + " v"+getDescription().getVersion() + " is enabled.");
        checkcmd();
        checkConfig();
        checkUpdate();
        commands();
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
    }

    public void onDisable() {
        this.getLogger().info(getDescription().getName() + " v"+getDescription().getVersion() + " is disabled.");
    }
    
    public void commands()
    {
        getCommand("simpleautomessage").setExecutor(new CommandBouncerCommand(this));
    }

    public void checkConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.getConfig().options().copyHeader(true);

            System.out.println("[" + getDescription().getName() + "] No config.yml detected, config.yml created");
        }
    }
    
    public void checkUpdate(){
        if (getConfig().getBoolean("auto-update") == true) {
            Updater updater = new Updater(this, 59012, this.getFile(), Updater.UpdateType.DEFAULT, true);
        } else {
        }
    }



    
    public static int a = 1; // cmd checker
    public static int b = 0; // Error checker
    public static int c = 3; // Max errors allowed int

    public void checkcmd() {
        a = 1;
        b = 0;
        c = 3;
        for (a = a; b != c; a++) {
            if (getConfig().contains("cmd" + a)) {
            } else {
                b++;
            }
        }

        if (b == c) {
            if (getConfig().getBoolean("debug") == true) {
                System.out.println("While loop closed");
            }
            getLogger().info("Loaded " + a + " cmds from the config!");
        }
    }

    public void onError(String errormsg) {
        System.out.println("============ CommandBouncer ============");
        System.out.println("ERROR MESSAGE STARTING: ");
        System.out.println("");
        System.out.println("Error: " + errormsg);
        System.out.println();
        System.out.println("!PLUGIN DISABLED!");
        System.out.println("========================================");
        errormsg = null;
        this.getServer().getPluginManager().disablePlugin(this);
    }
    public void badpermsPlayer(Player p){
        p.sendMessage(ChatColor.RED + "Error: You don't have permission to perform that action!");
    }
    public void badpermsSender(CommandSender p){
        
    }
    
    public void senddebug(String s)
    {
        if (plugin.getConfig().getBoolean("debug") == true) {
            plugin.getLogger().log(Level.INFO, "[Debug] " + s);
        }
    }
    
}
