package com.carlgo11.CommandBouncer;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class Checks {

    public static boolean checkDisOutput(CommandBouncer plugin, String cmd)
    {
        if (plugin.getConfig().getStringList("disabled-commands").contains(cmd)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDisWorld(CommandBouncer plugin, World world)
    {
        if (plugin.getConfig().getStringList("disabled-worlds").contains(world.getName())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDisPlayer(CommandBouncer plugin, Player p)
    {
        if (plugin.getConfig().getStringList("ignored-players").contains(p.getName())) {
            return true;
        } else {
            return false;
        }
    }

}
