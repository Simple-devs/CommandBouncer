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
 */
public class CommandListener implements Listener {

    CommandBouncer plugin;

    public CommandListener(CommandBouncer plug) {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        System.out.println("chat registred!");
        System.out.println("a = " + CommandBouncer.a);
        System.out.println("b = " + CommandBouncer.b);
        Player player = e.getPlayer();
        String cmd = e.getMessage();
        final String[] asd = e.getMessage().split(" ");

        for (int a = 1; a < CommandBouncer.b; a++) { // cmdbnc.a ! b
            if (plugin.getConfig().getBoolean("debug") == true) {
            System.out.println("forloop started!");
            }
            if (e.getMessage().equalsIgnoreCase("/" + plugin.getConfig().getString("cmd" + a))) {
                if (plugin.getConfig().getBoolean("debug") == true) {
                System.out.println("matches cmd" + a);
                }


                if (player.hasPermission("CommandBouncer.listen.cmd" + a) || player.hasPermission("CommandBouncer.listen.*") || player.hasPermission("CommandBouncer.*")) {
                    //if (!plugin.getConfig().getString("ignore-worlds").equalsIgnoreCase(player.getWorld() + "")) {
                    if (plugin.getConfig().contains("console" + a)) {
                        String dastring = plugin.getConfig().getString("console" + a);
                        String replaceAlla = dastring.replaceAll("%player%", player.getName());
                        if (plugin.getConfig().getBoolean("debug") == true) {
                        System.out.println("dastring:" + replaceAlla);
                        System.out.println("Console bnc string found!");
                        }
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), replaceAlla);
                        if (plugin.getConfig().getBoolean("debug") == true) {
                        System.out.println("Console" + a + ": " + plugin.getConfig().getString("Console" + a));
                        }
                    } else {
                        if (plugin.getConfig().getBoolean("debug") == true) {
                        System.out.println("No console bnc string found!");
                        }
                    }
                    if (plugin.getConfig().contains("player" + a)) {
                        String dastring = plugin.getConfig().getString("player" + a);
                        String replaceAlla = dastring.replaceAll("%player%", player.getName());
                            System.out.println("dastring:" + dastring);
                        System.out.println("player bnc string found!");
                        Bukkit.getServer().dispatchCommand(Bukkit.getPlayer(e.getPlayer().getName()), replaceAlla);
                    } else {
                        if (plugin.getConfig().getBoolean("debug") == true) {
                        System.out.println("No player bnc string found!");
                        }
                    }

                    //}
                }
            } else {
                if (plugin.getConfig().getBoolean("debug") == true) {
                    System.out.println("No match: cmd" + a);
                    System.out.println("player's-cmd:" + e.getMessage());
                    System.out.println("cmd" + a + ":" + plugin.getConfig().getString("cmd" + a));
                    System.out.println(" ");
                }
            }
        }
    }

    private void replaceAll(String player, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
