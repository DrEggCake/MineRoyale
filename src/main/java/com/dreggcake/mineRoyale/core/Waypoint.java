package com.dreggcake.mineRoyale.core;

import org.bukkit.Location;

public class Waypoint {
    private Location location;
    private Team team;

    public Waypoint(Location location, Team team) {
        this.location = location;
        this.team = team;
    }

    public Location getLocation() {
        return location;
    }

    public Team getTeam() {
        return team;
    }
}
