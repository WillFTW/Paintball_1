package net.paintball.wummeh;

import net.paintball.wummeh.listeners.PlayerInteract;
import net.paintball.wummeh.listeners.ProjectileHit;
import net.paintball.wummeh.tasks.BlockRemover;
import net.paintball.wummeh.utils.SQL;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Will on 10/3/2016.
 */
public class Main extends JavaPlugin {
    public static Plugin plugin;
    public void onEnable(){
        plugin = this;
        SQL.init();
        try {
            Statement s = SQL.conn.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS paintball(UUID varchar(255),PAINTBALLS int(11))");
        } catch (Exception ex){
            ex.printStackTrace();
        }
        BlockRemover.init();
        this.getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        this.getServer().getPluginManager().registerEvents(new ProjectileHit(), this);
        this.getServer().getLogger().info("Paintball has been enabled!");
    }
    public void onDisable(){
        this.getServer().getLogger().info("Paintball has been disabled!");
    }
}
