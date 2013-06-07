package commandbouncer.carlgo11.CommandBouncer;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;
import commandbouncer.carlgo11.CommandBouncer.player.*;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Carlgo11
 */
public class CommandBouncer extends JavaPlugin {
public static CommandBouncer plugin;
    public final static Logger logger = Logger.getLogger("Minercraft");

    public void onEnable() {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.getConfig().options().copyHeader(true);

            System.out.println("[" + getDescription().getName() + "] No config.yml detected, config.yml created");
        }
        this.getLogger().info("[" + getDescription().getName() + "] " + getDescription().getName() + " " + getDescription().getVersion() + " is enabled!");
        checkcmd();
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
    }

    public void onDisable() {
        this.getLogger().info("[" + getDescription().getName() + "] " + getDescription().getName() + " " + getDescription().getVersion() + " is disabled!");
    }

    @Override
    public boolean onCommand(final CommandSender sender, Command cmd, String commandLabel, String[] args) {
        return true;
    }
    
    public static int a = 1;
    public static int b = 0;
    public void checkconfigfile() {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.getConfig().options().copyHeader(true);
            System.out.println("[" + getDescription().getName() + "] No config.yml detected, config.yml created");
        }
    }
    
     static public void checkcmd() {


        while (a != b) {
            if (plugin.getConfig().getString("list-cmds").contains("cmd" + b)) {
                a++;
                System.out.println("a=" + a);
                System.out.println("b=" + b);
            }
        }  
        if (a == b) {
            System.out.println("while closed");
            b = a;
        }
    }
}
