package net.paintball.wummeh.listeners;

import net.paintball.wummeh.utils.SQL;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Will on 10/3/2016.
 */
public class PlayerInteract implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (e.getAction() == null || e.getItem() == null){
            return;
        }
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)){
            if (e.getItem().getType().equals(Material.DIAMOND_BARDING)){
                if (e.getPlayer().getInventory().contains(Material.GOLD_NUGGET)){
                    e.getPlayer().getInventory().removeItem(new ItemStack(Material.GOLD_NUGGET, 1));
                    e.getPlayer().launchProjectile(Snowball.class, e.getPlayer().getLocation().getDirection().multiply(1.5));
                    try {
                        Statement s = SQL.conn.createStatement();
                        ResultSet rs = s.executeQuery("SELECT * FROM paintball WHERE UUID='" + p.getUniqueId().toString() + "'");
                        if (rs.next()){
                            Integer i = rs.getInt("PAINTBALLS")+1;
                            s.execute("UPDATE paintball SET PAINTBALLS=" + i + " WHERE UUID='" + p.getUniqueId().toString() + "'");
                        }
                        else {
                            s.execute("INSERT INTO paintball VALUES('" + p.getUniqueId().toString() + "',1)");
                        }
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
                else {
                    p.sendMessage("You don't have enough ammo!");
                }
            }
        }
    }

}
