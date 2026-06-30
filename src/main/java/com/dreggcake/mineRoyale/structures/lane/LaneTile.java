package com.dreggcake.mineRoyale.structures.lane;

import org.bukkit.Location;

public class LaneTile {

    private int index;
    private Location location;

    public LaneTile(int index, Location location) {
        this.index = index;
        this.location = location;
    }

    public int getIndex() {
        return index;
    }

    public Location getLocation() {
        return location;
    }
}
