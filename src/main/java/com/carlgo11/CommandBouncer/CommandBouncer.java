package com.carlgo11.CommandBouncer;

import com.carlgo11.CommandBouncer.Commands.*;
import com.carlgo11.CommandBouncer.mcstats.*;
import com.carlgo11.CommandBouncer.updater.Updater;
import com.carlgo11.CommandBouncer.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBouncer extends JavaPlugin {

    public static CommandBouncer plugin;
    public boolean update = false;
    public String configversion = "1.0";

    public void onEnable()
    {
        this.reloadConfig();
        checkcmd();
        checkConfig();
        checkUpdate();
        commands();
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        this.getLogger().info(getDescription().getName() + " v" + getDescription().getVersion() + " is enabled.");
    }

    public void onDisable()
    {
        this.getLogger().info(getDescription().getName() + " v" + getDescription().getVersion() + " is disabled.");
    }

    public void commands()
    {
        getCommand("CommandBouncer").setExecutor(new CommandBouncerCommand(this));
    }

    public void checkConfig()
    {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.getConfig().options().copyHeader(true);

            this.getLogger().info("[" + getDescription().getName() + "] No config.yml detected, config.yml created");
        }else{
            if(getConfig().getBoolean("update-config")){
            String cfgv = getConfig().getString("config-version");
            if(!cfgv.matches(configversion)){
                config.renameTo(new File(getDataFolder(), "config.old.version-" + getConfig().getString("config-version") + ".yml"));
                this.saveDefaultConfig();
                this.getConfig().options().copyHeader(true);
                this.getLogger().info("Config mismatch. Made a new version. The old config can be found as "+"config.old.version-" + cfgv + ".yml");
            }
                }
        }
    }

    public void checkUpdate()
    {
        if (getConfig().getBoolean("auto-update")) {
            Updater updater = new Updater(this, 59012, this.getFile(), Updater.UpdateType.DEFAULT, true);
        } else if(!getConfig().getString("warn-update").equalsIgnoreCase("none")){
            Updater updater = new Updater(this, 59012, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, true);
            update = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
        }
    }
    
    public void checkMetrics()
    {
        try {
            Metrics metrics = new Metrics(this);
            CustomGraphs.graphs(metrics, this);
            metrics.start();
        } catch (IOException ex) {
            System.out.println("[" + getDescription().getName() + "] " + "Error Submitting stats! " + "Output: " + ex.toString());
        }
    }
    
    public void forceUpdate(CommandSender p, String prefix)
    {
        p.sendMessage(prefix + " " + ChatColor.GREEN + "Downloading the latest "+getDescription().getName()+"...");
        Updater updater = new Updater(this, 59012, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
        p.sendMessage(prefix + " " + ChatColor.GREEN + getDescription().getName() + " updated!");
    }

    public static int a = 1; // cmd checker
    public static int b = 0; // Error checker
    public static int c = 1; // Max errors allowed int

    public void checkcmd()
    {

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
            this.getLogger().info("Loaded " + a + " cmds from the config!");
        }
    }

    public void onError(String errormsg)
    {
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

    public void badpermsPlayer(Player p)
    {
        p.sendMessage(ChatColor.RED + "Error: You don't have permission to perform that action!");
    }

    public void badpermsSender(CommandSender p)
    {
        p.sendMessage(ChatColor.RED + "Error: You don't have permission to perform that action!");
    }

    public void senddebug(String s)
    {
        if (this.getConfig().getBoolean("debug")) {
            this.getLogger().log(Level.INFO, "[Debug] " + s);
        }
    }

}
