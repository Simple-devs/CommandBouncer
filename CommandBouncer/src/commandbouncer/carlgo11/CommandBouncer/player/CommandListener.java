package commandbouncer.carlgo11.CommandBouncer.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import commandbouncer.carlgo11.CommandBouncer.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 *
 * @author Carlgo11
 *
 * Working, but I will add more!
 */
public class CommandListener implements Listener {

    CommandBouncer plugin;

    public CommandListener(CommandBouncer plug) {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (plugin.getConfig().getBoolean("debug") == true) {
        System.out.println("[" + plugin.getDescription().getName() + "] " + "chat registred!");
        System.out.println("[" + plugin.getDescription().getName() + "] " + "a = " + CommandBouncer.a);
        System.out.println("[" + plugin.getDescription().getName() + "] " + "b = " + CommandBouncer.b);
        }
        Player player = e.getPlayer();
        String cmd = e.getMessage();
        final String[] asd = e.getMessage().split(" ");

        for (int a = 1; a < CommandBouncer.b; a++) { // cmdbnc.a ! b
            if (plugin.getConfig().getBoolean("debug") == true) {
                System.out.println("[" + plugin.getDescription().getName() + "] " + "forloop started!");
            }
            if (e.getMessage().equalsIgnoreCase("/" + plugin.getConfig().getString("cmd" + a))) {
                if (plugin.getConfig().getBoolean("debug") == true) {
                    System.out.println("[" + plugin.getDescription().getName() + "] " + "matches cmd" + a);
                }
                if (player.hasPermission("CommandBouncer.listen.cmd" + a) || player.hasPermission("CommandBouncer.listen.*") || player.hasPermission("CommandBouncer.*")) { // Checks if player has permission
                    if (plugin.getConfig().getList("ignored-worlds").contains(player.getWorld())) {
                        if (!plugin.getConfig().getBoolean("debug") == true) {
                            System.out.println("[" + plugin.getDescription().getName() + "] " + player.getName() + " is in a disabled world!");
                        }
                    } else {
                        if (plugin.getConfig().getBoolean("debug") == true) {
                            System.out.println("[" + plugin.getDescription().getName() + "] " + player.getName() + " is not in a disabled world!");
                        }
                        e.setCancelled(true); // Disables the command
                        if (plugin.getConfig().contains("console" + a)) {
                            String dastring = plugin.getConfig().getString("console" + a);
                            String replaceAlla = dastring.replaceAll("%player%", player.getName());
                            if (plugin.getConfig().getBoolean("debug") == true) {
                                System.out.println("[" + plugin.getDescription().getName() + "] " + "dastring:" + replaceAlla);
                                System.out.println("[" + plugin.getDescription().getName() + "] " + "Console bnc string found!");
                            }
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), replaceAlla);
                            if (plugin.getConfig().getBoolean("debug") == true) {
                                System.out.println("[" + plugin.getDescription().getName() + "] " + "Console" + a + ": " + plugin.getConfig().getString("Console" + a));
                            }
                        } else {
                            if (plugin.getConfig().getBoolean("debug") == true) {
                                System.out.println("[" + plugin.getDescription().getName() + "] " + "No console bnc string found!");
                            }
                        }
                        if (plugin.getConfig().contains("player" + a)) {
                            String dastring = plugin.getConfig().getString("player" + a);
                            String replaceAlla = dastring.replaceAll("%player%", player.getName());
                            System.out.println("[" + plugin.getDescription().getName() + "] " + "dastring:" + dastring);
                            System.out.println("[" + plugin.getDescription().getName() + "] " + "player bnc string found!");
                            Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(e.getPlayer().getName()), replaceAlla);
                        } else {
                            if (plugin.getConfig().getBoolean("debug") == true) {
                                System.out.println("[" + plugin.getDescription().getName() + "] " + "No player bnc string found!");
                            }
                        }

                    }
                }
            } else {
                if (plugin.getConfig().getBoolean("debug") == true) {
                    System.out.println("[" + plugin.getDescription().getName() + "] " + "No match: cmd" + a);
                    System.out.println("[" + plugin.getDescription().getName() + "] " + "player's-cmd:" + e.getMessage());
                    System.out.println("[" + plugin.getDescription().getName() + "] " + "cmd" + a + ":" + plugin.getConfig().getString("cmd" + a));
                    System.out.println("[" + plugin.getDescription().getName() + "] " + " ");
                }
            }
        }
    }
}
