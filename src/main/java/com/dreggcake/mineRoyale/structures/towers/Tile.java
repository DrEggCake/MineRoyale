package com.dreggcake.mineRoyale.structures.towers;

import org.bukkit.Location;

public class Tile {
    private int index;
    private Location location;

    public Tile(int index, Location location) {
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
