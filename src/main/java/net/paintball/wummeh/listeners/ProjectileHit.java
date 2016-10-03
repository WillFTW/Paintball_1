package net.paintball.wummeh.listeners;

import net.minecraft.server.v1_7_R4.BlockRedstone;
import net.paintball.wummeh.tasks.BlockRemover;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

/**
 * Created by Will on 10/3/2016.
 */
public class ProjectileHit implements Listener {
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent e){
        if (e.getEntity() instanceof Snowball){
            Projectile p = e.getEntity();
            Location b = p.getLocation();
            b.setY(b.getBlockY()-1);
            BlockRemover.original.put(b, b.getBlock().getType());
            b.getBlock().setType(Material.DIAMOND_BLOCK);
            BlockRemover.time.put(b, 1);
        }
    }
}
