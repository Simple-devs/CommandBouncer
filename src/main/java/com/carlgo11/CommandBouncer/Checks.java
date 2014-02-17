package com.carlgo11.CommandBouncer;

import org.bukkit.World;
import org.bukkit.entity.Player;

public class Checks {

    public static boolean checkDisOutput(CommandBouncer plugin, String cmd)
    {
        return (plugin.getConfig().getStringList("disabled-commands").contains(cmd));
    }

    public static boolean checkDisWorld(CommandBouncer plugin, World world)
    {
        return (plugin.getConfig().getStringList("disabled-worlds").contains(world.getName()));
    }

    public static boolean checkDisPlayer(CommandBouncer plugin, Player p)
    {
        return (plugin.getConfig().getStringList("ignored-players").contains(p.getName()));
    }

}
