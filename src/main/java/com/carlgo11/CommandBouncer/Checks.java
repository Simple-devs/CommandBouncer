package com.carlgo11.CommandBouncer;

import org.bukkit.World;

public class Checks {
    
    public static boolean checkDisOutput(CommandBouncer plugin, String cmd){
         if (plugin.getConfig().getList("disabled-commands").contains(cmd)) {
                            return true;
                    }else{
             return false;
         }
    }
    
    public static boolean checkDisWorld(CommandBouncer plugin, World world){
     if(plugin.getConfig().getList("disabled-worlds").contains(world)){
         return true;
     }else{
         return false;
     }   
    }

}
