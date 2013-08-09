package com.carlgo11.CommandBouncer;

import com.carlgo11.CommandBouncer.updater.Updater;
import com.carlgo11.CommandBouncer.metrics.Metrics;
import com.carlgo11.CommandBouncer.player.CommandListener;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandBouncer extends JavaPlugin {

    public static CommandBouncer plugin;
    public static String errormsg = null;

    public void onEnable() {
        this.reloadConfig();
        this.getLogger().info("[" + getDescription().getName() + "] " + getDescription().getName() + " " + getDescription().getVersion() + " is enabled");
        checkcmd();
        checkConfig();
        checkMetrics();
        checkUpdate();
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
    }

    public void onDisable() {
        this.getLogger().info(getDescription().getName() + " " + getDescription().getVersion() + " is disabled!");
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
            Updater updater = new Updater(this, "commandbouncer/", this.getFile(), Updater.UpdateType.DEFAULT, true);
        } else {
        }
    }

    public void checkMetrics() {
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            System.out.println("[" + getDescription().getName() + "] Error Submitting stats!");
        }
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args) {         //Used when we wan't to add a command fairly :P
        String prefix = ChatColor.GREEN + "[" + getDescription().getName() + "] " + ChatColor.RESET;
        String badperm = ChatColor.RED + "Error: You don't have permission to perform that action!";

        if (cmd.getName().equalsIgnoreCase("CommandBouncer")) {
            if (args.length == 0) { // EXAMPLE: /cmd arg0 arg1 arg2
                if (sender.hasPermission("commandbouncer.cmd.commandbouncer") || sender.hasPermission("CommandBouncer.*")) {
                    sender.sendMessage(ChatColor.YELLOW + "============ " + ChatColor.GREEN + getDescription().getName() + ChatColor.YELLOW + " ============");
                    sender.sendMessage("");
                    sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer" + ChatColor.YELLOW + " Shows all avible commands");
                    sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer Reload" + ChatColor.YELLOW + " Reload the config.yml");
                    sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer List" + ChatColor.YELLOW + " List all the commands that the plugin listens on");
                } else {
                    sender.sendMessage(prefix + badperm);
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("commandbouncer.cmd.commandbouncer.reload") || sender.hasPermission("CommandBouncer.*")) {
                        getServer().getPluginManager().disablePlugin(this);
                        getServer().getPluginManager().enablePlugin(this);
                        sender.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");
                    } else {
                        sender.sendMessage(prefix + badperm);
                    }
                }

                if (args[0].equalsIgnoreCase("list")) {
                    if (sender.hasPermission("commandbouncer.cmd.commandbouncer.list") || sender.hasPermission("CommandBouncer.*")) {
                        sender.sendMessage(ChatColor.YELLOW + "============ " + ChatColor.GREEN + prefix + ChatColor.YELLOW + " ============");
                        sender.sendMessage(ChatColor.GRAY+"Gray = Command to listen to.");
                        sender.sendMessage(ChatColor.BLUE+"Blue = Console command.");
                        sender.sendMessage(ChatColor.RED+"Red = Player command.");
                        int d = 1;
                        int e = 0;
                        int f = 1;
                        StringBuilder message = new StringBuilder();
                        for (d = d; e != f; d++) {
                            if (getConfig().contains("cmd" + d)) {
                                message.append("\n" + ChatColor.YELLOW + "cmd" + d + ": " + ChatColor.GRAY);
                                message.append("/" + getConfig().getString("cmd" + d)+"");
                                if (getConfig().contains("console" + d)) {
                                    message.append(ChatColor.RESET+" " + ChatColor.BLUE +"/"+ getConfig().getString("console" + d));
                                }
                                if (getConfig().contains("player" + d)) {
                                    message.append(ChatColor.RESET+" " + ChatColor.RED +"/"+ getConfig().getString("player" + d));
                                }
                            } else {
                                e++;
                            }
                        }
                        sender.sendMessage("" + message);
                    } else {
                        sender.sendMessage(prefix + badperm);
                    }
                    // more 1 arg commands here:
                }
            } else if (args.length == 2) {
                // Add code here?
            }
        }
        return true;
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

            int plaina = a -= 2;
            getLogger().info("Loaded " + a + " cmds from the config!");
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
