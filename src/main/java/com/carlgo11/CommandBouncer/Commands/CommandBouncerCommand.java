package com.carlgo11.CommandBouncer.Commands;

import com.carlgo11.CommandBouncer.CommandBouncer;
import com.carlgo11.CommandBouncer.Report;
import com.carlgo11.CommandBouncer.pastebin.Pastebin;
import java.io.UnsupportedEncodingException;
import org.bukkit.ChatColor;
import org.bukkit.command.*;

public class CommandBouncerCommand implements CommandExecutor {

    private CommandBouncer plugin;

    public CommandBouncerCommand(CommandBouncer plug)
    {
        this.plugin = plug;
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        String prefix = ChatColor.GREEN + "[" + plugin.getDescription().getName() + "] " + ChatColor.RESET;

        if (args.length == 0) {
            help(sender);
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                reload(sender, prefix);
            } else if (args[0].equalsIgnoreCase("list")) {
                list(sender, prefix);
            } else if (args[0].equalsIgnoreCase("report")) {
                report(sender, prefix);
            } else if (args[0].equalsIgnoreCase("support")) {
                support(sender, prefix);
            }
        } else if (args.length == 2) {

        }
        return true;
    }

    void help(CommandSender sender)
    {
        if (sender.hasPermission("commandbouncer.cmd.commandbouncer")) {
            sender.sendMessage(ChatColor.YELLOW + "============ " + ChatColor.GREEN + plugin.getDescription().getName() + ChatColor.YELLOW + " ============");
            sender.sendMessage("");
            sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer" + ChatColor.YELLOW + " Shows all avible commands");
            sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer Reload" + ChatColor.YELLOW + " Reload the config.yml");
            sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer Report" + ChatColor.YELLOW + " Upload a report to pastebin");
            sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer Support" + ChatColor.YELLOW + " Get help from a developer");
            sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "CommandBouncer List" + ChatColor.YELLOW + " List all the commands that the plugin listens on");
        } else {
            plugin.badpermsSender(sender);
        }
    }

    void list(CommandSender sender, String prefix)
    {
        if (sender.hasPermission("commandbouncer.cmd.commandbouncer.list")) {
            sender.sendMessage(ChatColor.YELLOW + "============ " + ChatColor.GREEN + prefix + ChatColor.YELLOW + " ============");
            sender.sendMessage(ChatColor.GRAY + "Gray = Command to listen to.");
            sender.sendMessage(ChatColor.BLUE + "Blue = Console command.");
            sender.sendMessage(ChatColor.RED + "Red = Player command.");
            int d = 1;
            int e = 0;
            int f = 1;
            StringBuilder message = new StringBuilder();
            for (d = d; e != f; d++) {
                if (plugin.getConfig().contains("cmd" + d)) {
                    message.append("\n" + ChatColor.YELLOW + "cmd" + d + ": " + ChatColor.GRAY);
                    message.append("/" + plugin.getConfig().getString("cmd" + d) + "");
                    if (plugin.getConfig().contains("console" + d)) {
                        message.append(ChatColor.RESET + " " + ChatColor.BLUE + "/" + plugin.getConfig().getStringList("console" + d).toString());
                    }
                    if (plugin.getConfig().contains("player" + d)) {
                        message.append(ChatColor.RESET + " " + ChatColor.RED + "/" + plugin.getConfig().getString("player" + d));
                    }
                } else {
                    e++;
                }
            }
            sender.sendMessage("" + message);
        } else {
            plugin.badpermsSender(sender);
        }
    }

    void reload(CommandSender sender, String prefix)
    {
        if (sender.hasPermission("commandbouncer.cmd.commandbouncer.reload")) {
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            plugin.getServer().getPluginManager().enablePlugin(plugin);
            sender.sendMessage(prefix + ChatColor.GREEN + "Config reloaded!");
        } else {
            plugin.badpermsSender(sender);
        }
    }

    void report(CommandSender sender, String prefix)
    {
        if (sender.hasPermission("commandbouncer.cmd.commandbouncer.report")) {
            try {
                String pastebin = Pastebin.makePaste(Report.Main(plugin), plugin.getDescription().getName());
                sender.sendMessage("" + prefix + ChatColor.GREEN + "Here's your log: " + pastebin);
            } catch (UnsupportedEncodingException ex) {
                sender.sendMessage(prefix + "Error: " + ex.toString());
                plugin.senddebug(ex.toString());
            }
        } else {
            plugin.badpermsSender(sender);
        }
    }

    void support(CommandSender sender, String prefix)
    {
        if (sender.hasPermission("commandbouncer.cmd.commandbouncer.support")) {
            try {
                String pastebin = Pastebin.makePaste(Report.Main(plugin), plugin.getDescription().getName());
                String purelink = pastebin.toString().replace("http://pastebin.com/", "");
                sender.sendMessage("" + prefix + ChatColor.GREEN + "Thank you for choosing our support IRC!\nIf the helpers busy please post a question on bukkit.");
                sender.sendMessage(ChatColor.YELLOW + "Connect with this link: " + ChatColor.BLUE + "http://cajs.co.uk/link/msg-irc?&nick=cmdbnc_" + purelink);
                sender.sendMessage(ChatColor.YELLOW + "Here's your log: " + ChatColor.BLUE + pastebin + "\n" + ChatColor.GREEN + "Please give the developers your log.");
            } catch (UnsupportedEncodingException ex) {
                sender.sendMessage("Error: " + ex.toString());
                plugin.senddebug(ex.toString());
            }
        } else {
            plugin.badpermsSender(sender);
        }
    }
}
