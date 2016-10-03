package net.paintball.wummeh.tasks;

import net.paintball.wummeh.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Will on 10/3/2016.
 */
public class BlockRemover {
    public static HashMap<Location, Integer> time = new HashMap<Location, Integer>();
    public static HashMap<Location, Material> original = new HashMap<Location, Material>();
    public static void init() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.plugin, new Runnable() {
            public void run() {
                for (Location b : time.keySet()) {
                    if (time.get(b).equals(0)){
                        b.getBlock().setType(original.get(b));
                        original.remove(b);
                        time.remove(b);
                    }
                    else {
                        time.put(b, time.get(b)-1);
                    }
                }
            }
        }, 20, 20);
    }
}
