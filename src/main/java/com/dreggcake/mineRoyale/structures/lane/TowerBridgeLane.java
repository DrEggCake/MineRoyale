package com.dreggcake.mineRoyale.structures.lane;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class TowerBridgeLane {
    private Location firstTileLocation;
    private int totalTiles =  16;
    private List<LaneTile> laneTiles = new ArrayList<>();

    public TowerBridgeLane(Location firstTileLocation) {
        this.firstTileLocation = firstTileLocation;
        calculateLane();
    }

    private void calculateLane() {
        for (int i = 0; i < totalTiles; i++) {
            Location location = firstTileLocation.clone();
            location.setY(location.getY() + i);
            LaneTile tile = new LaneTile(i, location);
            laneTiles.add(tile);
        }

    }


}
