package com.carlgo11.CommandBouncer.player;

import com.carlgo11.CommandBouncer.CommandBouncer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    CommandBouncer plugin;

    public PlayerJoin(CommandBouncer plug)
    {
        super();
        this.plugin = plug;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        if (!plugin.getConfig().getBoolean("auto-update")) {
            String upd = plugin.getConfig().getString("warn-update");
            Player p = e.getPlayer();
            String prefix = ChatColor.LIGHT_PURPLE + "[" + plugin.getDescription().getName() + "]" + ChatColor.RESET;
            String updateav = prefix + ChatColor.GREEN + " An update is available!\nType " + ChatColor.BLUE + ChatColor.ITALIC + "/commandbouncer update" + ChatColor.RESET + ChatColor.GREEN + " to update the plugin.";
            if (upd.equalsIgnoreCase("op")) {
                if (p.isOp() && plugin.update) {
                    p.sendMessage(updateav);
                }
            } else if (upd.equalsIgnoreCase("perm")) {
                if (p.hasPermission("commandbouncer.notifyupdate") && plugin.update) {
                    p.sendMessage(updateav);
                }
            }
        }
    }

}
