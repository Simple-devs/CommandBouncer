package commandbouncer.carlgo11.CommandBouncer;

import org.bukkit.plugin.java.JavaPlugin;
import commandbouncer.carlgo11.CommandBouncer.player.*;
import java.io.File;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Carlgo11
 */
public class CommandBouncer extends JavaPlugin {

    public static CommandBouncer plugin;
    public static String errormsg = null;

    public void onEnable() {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.getConfig().options().copyHeader(true);

            System.out.println("[" + getDescription().getName() + "] No config.yml detected, config.yml created");
        }
        this.getLogger().info("[" + getDescription().getName() + "] " + getDescription().getName() + " " + getDescription().getVersion() + " is enabled!");
        try {
				Metrics metrics = new Metrics(this); metrics.start();
				} catch (IOException e) { 
				System.out.println("[" + getDescription().getName() + "] Error Submitting stats!");
				}
        //checkcmd();
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
    }

    public void onDisable() {
        this.getLogger().info("[" + getDescription().getName() + "] " + getDescription().getName() + " " + getDescription().getVersion() + " is disabled!");
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args) {         //Used when we wan't to add a command fairly :P
        String prefix = ChatColor.GREEN + "[" + getDescription().getName() + "] " + ChatColor.RESET;
        String badperm = ChatColor.RED + "Error: You don't have permission to perform that action!";
        if (cmd.getName().equalsIgnoreCase("CommandBouncer")) {
            
            if (args.length == 0) { // EXAMPLE: /cmd arg0 arg1 arg2
                //Menu
                if(sender.hasPermission("commandbouncer.cmd.commandbouncer")){
                sender.sendMessage(ChatColor.YELLOW + "============ " + ChatColor.GREEN + getDescription().getName() + ChatColor.YELLOW + " ============");
                sender.sendMessage("");
                sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer" + ChatColor.YELLOW + " Shows all avible commands");
                sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer Reload" + ChatColor.YELLOW + " Reload the config.yml");
            } else {
                    sender.sendMessage(prefix + badperm);
                }
            } else if (args.length == 1) {
                
                if (args[0].equalsIgnoreCase("reload")) {
                    if(sender.hasPermission("commandbouncer.cmd.commandbouncer.reload")) {
                    this.reloadConfig();
                    //checkcmd();
                    sender.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");
                } else {
                       sender.sendMessage(prefix + badperm);
                    }
                } else {
                }
            } else if (args.length == 2) {
            }
        }
        return true;
    }
    public static int a = 0;
    public static int b = 20;

    public void checkconfigfile() {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.getConfig().options().copyHeader(true);
            System.out.println("[" + getDescription().getName() + "] No config.yml detected, config.yml created");
        }
    }

    public void checkcmd() {

        while (a != b) {
            if (getConfig().contains("cmd" + a)) {
                a++;
                System.out.println("a=" + a);
                System.out.println("b=" + b);
            } else {
                if (a == 1) {
                    errormsg = "No cmds set!";
                    onError();
                }
            }
        }

        if (a == b) {
            System.out.println("while closed");
            b = a;
        }
    }

    public void onError() {                                                   
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
}