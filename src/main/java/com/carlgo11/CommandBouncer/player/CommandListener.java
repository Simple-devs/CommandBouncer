package com.carlgo11.CommandBouncer.player;

import com.carlgo11.CommandBouncer.Checks;
import com.carlgo11.CommandBouncer.CommandBouncer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {

    CommandBouncer plugin;

    public CommandListener(CommandBouncer plug) {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        plugin.senddebug("a = " + CommandBouncer.a);
        plugin.senddebug("b = " + CommandBouncer.b);
        Player player = e.getPlayer();
        String cmd = e.getMessage();

        for (int a = 1; a != CommandBouncer.a; a++) {
            plugin.senddebug("forloop started!");

            String[] splitCommand = e.getMessage().split(" ");
            // Iterate through the split command
            if (plugin.getConfig().contains(splitCommand[0] + " *")) {
                e.setMessage(splitCommand[0]);
        }

            if (e.getMessage().equalsIgnoreCase("/" + plugin.getConfig().getString("cmd" + a)) || (plugin.getConfig().getString("cmd" + a).equalsIgnoreCase(e.getMessage() + " *"))) {
                plugin.senddebug("matches cmd" + a);
                if (!Checks.checkDisPlayer(plugin, player)) {
                    if (!Checks.checkDisWorld(plugin, player.getWorld())) {
                        if (player.hasPermission("CommandBouncer.listen.cmd" + a) || player.hasPermission("CommandBouncer.listen.cmd*")) { // Checks if player has permission

                            if (Checks.checkDisOutput(plugin, "cmd" + a)) {
                                e.setCancelled(true);
                                System.out.println(player.getName() + " issued server command: " + cmd);
                            }
                            if (plugin.getConfig().contains("console" + a)) {
                                for (int i = 0; i < plugin.getConfig().getStringList("console" + a).size(); i++) {
                                    String curcmd = plugin.getConfig().getStringList("console" + a).get(i);
                                    String replaceinput = curcmd.replaceAll("%player%", player.getName());
                                    String replaceinput2 = replaceinput.replaceAll("%world%", player.getWorld().getName());
                                    plugin.senddebug("dastring:" + replaceinput);
                                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), replaceinput2);
                                    plugin.senddebug("Console" + a + ": " + curcmd);
                                }
                            } else {
                                plugin.senddebug("No console bnc string found!");
                            }

                            if (plugin.getConfig().contains("player" + a)) {
                                for (int i = 0; i < plugin.getConfig().getStringList("player" + a).size(); i++) {
                                    String curcmd = plugin.getConfig().getStringList("player" + a).get(i);
                                    String replaceinput = curcmd.replaceAll("%player%", player.getName());
                                    String replaceinput2 = replaceinput.replaceAll("%world%", player.getWorld().getName());
                                    plugin.senddebug("dastring:" + replaceinput);
                                    Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(e.getPlayer().getName()), replaceinput2);
                                    plugin.senddebug("Console" + a + ": " + curcmd);
                                }
                            } else {
                                plugin.senddebug("No player bnc string found!");
                            }

                        } else {
                            plugin.senddebug(player.getName() + " don't have permission for cmd" + a);
                        }

                    } else {
                        plugin.senddebug(player.getWorld().getName() + " is a disabled world.");
                    }

                } else {
                    plugin.senddebug(player.getName() + " is an ignored player.");
                }

            } else {
                if (plugin.getConfig().getBoolean("debug")) {
                    System.out.println("[" + plugin.getDescription().getName() + "] " + "No match: cmd" + a);
                    System.out.println("[" + plugin.getDescription().getName() + "] " + player.getName() + "'s-cmd:" + e.getMessage());
                    System.out.println("[" + plugin.getDescription().getName() + "] " + "cmd" + a + ":" + plugin.getConfig().getString("cmd" + a));
                }
            }
        }
    }

}