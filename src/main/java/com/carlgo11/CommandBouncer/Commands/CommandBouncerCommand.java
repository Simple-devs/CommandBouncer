package com.carlgo11.CommandBouncer.Commands;

import com.carlgo11.CommandBouncer.CommandBouncer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandBouncerCommand implements CommandExecutor {

    private CommandBouncer plugin;

    public CommandBouncerCommand(CommandBouncer plug)
    {
        this.plugin = plug;
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args) {         //Used when we wan't to add a command fairly :P
        String prefix = ChatColor.GREEN + "[" + plugin.getDescription().getName() + "] " + ChatColor.RESET;

            if (args.length == 0) { // EXAMPLE: /cmd arg0 arg1 arg2
                if (sender.hasPermission("commandbouncer.cmd.commandbouncer")) {
                    sender.sendMessage(ChatColor.YELLOW + "============ " + ChatColor.GREEN + plugin.getDescription().getName() + ChatColor.YELLOW + " ============");
                    sender.sendMessage("");
                    sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer" + ChatColor.YELLOW + " Shows all avible commands");
                    sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer Reload" + ChatColor.YELLOW + " Reload the config.yml");
                    sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer List" + ChatColor.YELLOW + " List all the commands that the plugin listens on");
                } else {
                    plugin.badpermsSender(sender);
                }
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("commandbouncer.cmd.commandbouncer.reload")) {
                        plugin.getServer().getPluginManager().disablePlugin(plugin);
                        plugin.getServer().getPluginManager().enablePlugin(plugin);
                        sender.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");
                    } else {
                        plugin.badpermsSender(sender);
                    }
                }

                if (args[0].equalsIgnoreCase("list")) {
                    if (sender.hasPermission("commandbouncer.cmd.commandbouncer.list")) {
                        sender.sendMessage(ChatColor.YELLOW + "============ " + ChatColor.GREEN + prefix + ChatColor.YELLOW + " ============");
                        sender.sendMessage(ChatColor.GRAY+"Gray = Command to listen to.");
                        sender.sendMessage(ChatColor.BLUE+"Blue = Console command.");
                        sender.sendMessage(ChatColor.RED+"Red = Player command.");
                        int d = 1;
                        int e = 0;
                        int f = 1;
                        StringBuilder message = new StringBuilder();
                        for (d = d; e != f; d++) {
                            if (plugin.getConfig().contains("cmd" + d)) {
                                message.append("\n" + ChatColor.YELLOW + "cmd" + d + ": " + ChatColor.GRAY);
                                message.append("/" + plugin.getConfig().getString("cmd" + d)+"");
                                if (plugin.getConfig().contains("console" + d)) {
                                    message.append(ChatColor.RESET+" " + ChatColor.BLUE +"/"+ plugin.getConfig().getString("console" + d));
                                }
                                if (plugin.getConfig().contains("player" + d)) {
                                    message.append(ChatColor.RESET+" " + ChatColor.RED +"/"+ plugin.getConfig().getString("player" + d));
                                }
                            } else {
                                e++;
                            }
                        }
                        sender.sendMessage("" + message);
                    } else {
                        plugin.badpermsSender(sender);
                    }
                    // more 1 arg commands here:
                }
            } else if (args.length == 2) {
                // Add code here?
            }
        return true;
    }
}
