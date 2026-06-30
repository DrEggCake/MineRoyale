package com.dreggcake.mineRoyale.core;

import com.dreggcake.mineRoyale.MineRoyale;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class Unit {
    private LivingEntity livingEntity;
    private Team team;
    private int health;
    private Unit target;
    private Location waypointA;
    private Location waypointB;
    private Location currentWaypoint;

    MineRoyale plugin;

    public Unit(LivingEntity livingEntity, Team team, MineRoyale plugin){
        this.livingEntity = livingEntity;
        this.team = team;
        this.plugin = plugin;
        waypointA = new Location(plugin.getServer().getWorld("world"), 419.0, 92.0, 90.0);
        waypointB = new Location(plugin.getServer().getWorld("world"), 419.0, 92.0, 105.0);
    }

    public void update() {
        if (currentWaypoint == null)
            currentWaypoint = waypointA;
        Location current = livingEntity.getLocation();
        Vector direction = currentWaypoint.toVector().subtract(current.toVector()).normalize();
        direction.setY(0);

        livingEntity.setVelocity(direction.multiply(0.25));
    }
}
