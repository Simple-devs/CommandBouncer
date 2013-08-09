package com.carlgo11.CommandBouncer.player;

import com.carlgo11.CommandBouncer.CommandBouncer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 *
 * @author Carlgo11
 *
 */
public class CommandListener implements Listener {

    CommandBouncer plugin;

    public CommandListener(CommandBouncer plug) {
        super();
        this.plugin = plug;
    }
    String debugmsg = null;

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        debugmsg = "chat registred!";
        senddebug();
        debugmsg = "a = " + CommandBouncer.a;
        senddebug();
        debugmsg = "b = " + CommandBouncer.b;
        senddebug();
        Player player = e.getPlayer();
        String cmd = e.getMessage();
        final String[] asd = e.getMessage().split(" ");

        for (int a = 1; a != CommandBouncer.a; a++) {
            debugmsg = "forloop started!";
            senddebug();
            if (e.getMessage().equalsIgnoreCase("/" + plugin.getConfig().getString("cmd" + a))) {
                debugmsg = "matches cmd" + a;
                senddebug();
                if (plugin.getConfig().contains("cmd" + a + "-disable")) {
                    if (plugin.getConfig().getBoolean("cmd" + a + "-disable") == true) {
                        e.setCancelled(true);
                        System.out.println(player.getName() + " issued server command: " + cmd.toString());
                       
                    } else {
                    }
                } else {
                    e.setCancelled(true);
                    System.out.println(player.getName() + " issued server command: " + cmd.toString());
                }
                if (player.hasPermission("CommandBouncer.listen.cmd" + a) || player.hasPermission("CommandBouncer.listen.*") || player.hasPermission("CommandBouncer.*")) { // Checks if player has permission
                        debugmsg = player.getName() + " is not in a disabled world!";
                        senddebug();
                        if (plugin.getConfig().contains("console" + a)) {
                            String dastring = plugin.getConfig().getString("console" + a);
                            String replaceinput = dastring.replaceAll("%player%", player.getName());
                            String replaceinput2 = replaceinput.replaceAll("%world%", player.getWorld().getName());
                            debugmsg = "dastring:" + replaceinput;
                            senddebug();
                            debugmsg = "Console bnc string found!"; // Do we need to use this anymore? Waiting on second confirmation from another dev
                            senddebug();
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), replaceinput2);
                            debugmsg = "Console" + a + ": " + plugin.getConfig().getString("console" + a);
                            senddebug();
                        } else {
                            debugmsg = "No console bnc string found!";
                            senddebug();
                        }

                        if (plugin.getConfig().contains("player" + a)) {
                            String dastring = plugin.getConfig().getString("player" + a);
                            String replaceinput = dastring.replaceAll("%player%", player.getName());
                            String replaceinput2 = replaceinput.replaceAll("%world%", player.getWorld().getName());
                            debugmsg = "dastring:" + dastring;
                            senddebug();
                            debugmsg = "player bnc string found!";
                            senddebug();
                            Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(e.getPlayer().getName()), replaceinput2);
                        } else {
                            debugmsg = "No player bnc string found!";
                            senddebug();
                        }

                } else {
                    debugmsg = player.getName() + " don't have permission for cmd" + a;
                    senddebug();
                    if (plugin.getConfig().getBoolean("debug") == true) {
                        System.out.println("[" + plugin.getDescription().getName() + "] " + " ");
                    }
                }

            } else {
                if (plugin.getConfig().getBoolean("debug") == true) {
                    System.out.println("[" + plugin.getDescription().getName() + "] " + "No match: cmd" + a);
                    System.out.println("[" + plugin.getDescription().getName() + "] " + player.getName() + "'s-cmd:" + e.getMessage());
                    System.out.println("[" + plugin.getDescription().getName() + "] " + "cmd" + a + ":" + plugin.getConfig().getString("cmd" + a));
                    System.out.println("[" + plugin.getDescription().getName() + "] " + " ");
                }
            }
        }
    }

    public void senddebug() {  // This will make it easier to send debug messages to the console
        if (plugin.getConfig().getBoolean("debug") == true) {
            System.out.println("[" + plugin.getDescription().getName() + "] " + debugmsg);
            /**
             * To send debug messages use debugmsg = "debug message"; then to
             * send the message to this method use senddebug();
             *
             * Example: debugmsg = "hi"; senddebug();
             *
             * Result: [INFO] [CommandBouncer] hi
             *
             */
        }
    }
}
