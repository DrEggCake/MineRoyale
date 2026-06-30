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

    MineRoyale plugin;

    public Unit(LivingEntity livingEntity, Team team, MineRoyale plugin) {
        this.livingEntity = livingEntity;
        this.team = team;
        this.plugin = plugin;
    }

    public Unit getTarget() {
        return target;
    }

    public int getHealth() {
        return health;
    }

    public Team getTeam() {
        return team;
    }

    public LivingEntity getLivingEntity() {
        return livingEntity;
    }

    public void update() {
        Vector direction = PathFind.getDirection(plugin, this);

        livingEntity.setVelocity(direction.multiply(0.25));
    }
}