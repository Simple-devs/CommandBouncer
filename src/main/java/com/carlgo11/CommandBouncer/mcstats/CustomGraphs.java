package com.carlgo11.CommandBouncer.mcstats;

import com.carlgo11.CommandBouncer.CommandBouncer;

public class CustomGraphs {

    public static void graphs(Metrics metrics, CommandBouncer plugin)
    {
        graph1(metrics, plugin);
        graph2(metrics, plugin);
    }

    static void graph1(Metrics metrics, CommandBouncer plugin)
    {
        //Graph1
        Metrics.Graph graph1 = metrics.createGraph("auto-update"); //Sends data about how many msg strings the server has.
        if (plugin.getConfig().getBoolean("auto-update")) {
            graph1.addPlotter(new SimplePlotter("enabled"));
        } else {
            graph1.addPlotter(new SimplePlotter("disabled"));
        }
    }

    static void graph2(Metrics metrics, CommandBouncer plugin)
    {
        //Graph2
        Metrics.Graph graph1 = metrics.createGraph("warn-update"); //Sends data about how many msg strings the server has.
        if (plugin.getConfig().getString("warn-update").equalsIgnoreCase("op")) {
            graph1.addPlotter(new SimplePlotter("op"));
        } else if (plugin.getConfig().getString("warn-update").equalsIgnoreCase("perm")) {
            graph1.addPlotter(new SimplePlotter("perm"));
        } else if (plugin.getConfig().getString("warn-update").equalsIgnoreCase("none")) {
            {
                graph1.addPlotter(new SimplePlotter("none"));
            }
        }
    }

}
