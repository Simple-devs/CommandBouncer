package com.carlgo11.CommandBouncer.mcstats;

import com.carlgo11.CommandBouncer.CommandBouncer;

public class CustomGraphs {

    public static void graphs(Metrics metrics, CommandBouncer plugin)
    {
        graph1(metrics, plugin);
        graph2(metrics, plugin);
        graph3(metrics, plugin);
    }

    static void graph1(Metrics metrics, CommandBouncer plugin)
    {
        //Graph1
        Metrics.Graph graph = metrics.createGraph("auto-update"); //Sends data about how many msg strings the server has.
        if (plugin.getConfig().getBoolean("auto-update")) {
            graph.addPlotter(new SimplePlotter("enabled"));
        } else {
            graph.addPlotter(new SimplePlotter("disabled"));
        }
    }

    static void graph2(Metrics metrics, CommandBouncer plugin)
    {
        //Graph2
        Metrics.Graph graph = metrics.createGraph("warn-update"); //Sends data about how many msg strings the server has.
        if (plugin.getConfig().getString("warn-update").equalsIgnoreCase("op")) {
            graph.addPlotter(new SimplePlotter("op"));
        } else if (plugin.getConfig().getString("warn-update").equalsIgnoreCase("perm")) {
            graph.addPlotter(new SimplePlotter("perm"));
        } else if (plugin.getConfig().getString("warn-update").equalsIgnoreCase("none")) {
            {
                graph.addPlotter(new SimplePlotter("none"));
            }
        }
    }
    static void graph3(Metrics metrics, CommandBouncer plugin)
    {
        //Graph1
        Metrics.Graph graph = metrics.createGraph("update-config"); //Sends data about how many msg strings the server has.
        if (plugin.getConfig().getBoolean("update-config")) {
            graph.addPlotter(new SimplePlotter("enabled"));
        } else {
            graph.addPlotter(new SimplePlotter("disabled"));
        }
    }

}
