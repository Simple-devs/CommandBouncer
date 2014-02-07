package com.carlgo11.CommandBouncer.player;

import com.carlgo11.CommandBouncer.CommandBouncer;
import java.util.logging.Level;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 *
 * @author Carlgo11
 *
 */
public class CommandListener implements Listener {

    CommandBouncer plugin;

    public CommandListener(CommandBouncer plug)
    {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e)
    {
        senddebug("chat registred!");
        senddebug("a = " + CommandBouncer.a);
        senddebug("b = " + CommandBouncer.b);
        Player player = e.getPlayer();
        String cmd = e.getMessage();
        final String[] asd = e.getMessage().split(" ");

        for (int a = 1; a != CommandBouncer.a; a++) {
            senddebug("forloop started!");
            if (e.getMessage().equalsIgnoreCase("/" + plugin.getConfig().getString("cmd" + a))) {
                senddebug("matches cmd" + a);

                if (player.hasPermission("CommandBouncer.listen.cmd" + a) || player.hasPermission("CommandBouncer.listen.*") || player.hasPermission("CommandBouncer.*")) { // Checks if player has permission
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
                    senddebug(player.getName() + " is not in a disabled world!");
                    if (plugin.getConfig().contains("console" + a)) {
                        String dastring = plugin.getConfig().getString("console" + a);
                        String replaceinput = dastring.replaceAll("%player%", player.getName());
                        String replaceinput2 = replaceinput.replaceAll("%world%", player.getWorld().getName());
                        senddebug("dastring:" + replaceinput);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), replaceinput2);
                        senddebug("Console" + a + ": " + plugin.getConfig().getString("console" + a));
                    } else {
                        senddebug("No console bnc string found!");
                    }

                    if (plugin.getConfig().contains("player" + a)) {
                        String dastring = plugin.getConfig().getString("player" + a);
                        String replaceinput = dastring.replaceAll("%player%", player.getName());
                        String replaceinput2 = replaceinput.replaceAll("%world%", player.getWorld().getName());
                        senddebug("dastring:" + dastring);
                        senddebug("player bnc string found!");
                        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(e.getPlayer().getName()), replaceinput2);
                    } else {
                        senddebug("No player bnc string found!");
                    }

                } else {
                    senddebug(player.getName() + " don't have permission for cmd" + a);
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

    public void senddebug(String s)
    {  // This will make it easier to send debug messages to the console
        if (plugin.getConfig().getBoolean("debug") == true) {
            plugin.getLogger().log(Level.INFO, "[Debug] "+s);
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
