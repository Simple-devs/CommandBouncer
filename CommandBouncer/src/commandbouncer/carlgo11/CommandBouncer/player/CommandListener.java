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
        Player player = e.getPlayer();
        String cmd = e.getMessage();
        final String[] asd = e.getMessage().split(" ");

        for (int a = 1; a < CommandBouncer.b; a++) {
            System.out.println("forloop started!");
            if (e.getMessage().equalsIgnoreCase(plugin.getConfig().getString("cmd"+a))) {
                System.out.println("matches cmd"+a);
                if (player.hasPermission("CommandBouncer.listen.cmd" + a) || player.hasPermission("CommandBouncer.listen.*") || player.hasPermission("CommandBouncer.*")) {
                    if (plugin.getConfig().getString("ignore-worlds").equalsIgnoreCase(player.getWorld() + "")) {
                        if (plugin.getConfig().contains("console" + a)) {
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), plugin.getConfig().getString("console" + a));
                        }
                        if (plugin.getConfig().contains("player" + a)) {
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), plugin.getConfig().getString("player" + a));
                        }

                    }
                }
            }
        }
    }
}
