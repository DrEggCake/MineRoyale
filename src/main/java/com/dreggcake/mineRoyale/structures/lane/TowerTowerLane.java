package com.dreggcake.mineRoyale.structures.lane;

import com.dreggcake.mineRoyale.core.Team;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class TowerTowerLane {
    private int index;
    private Location firstTileLocation;
    private int totalTiles = 5;
    private List<LaneTile> laneTiles = new ArrayList<>();
    private Team team;

    public TowerTowerLane(int index, Location firstTileLocation, Team team) {
        this.index = index;
        this.firstTileLocation = firstTileLocation;
        this.team = team;
        calculateLane();
    }

    private void calculateLane() {
        for (int i = 0; i < totalTiles; i++) {
            Location location = firstTileLocation.clone();

            if (!(i < totalTiles - 1)) {
                switch (index) {
                    case 1 -> location.setX(location.getX() - i);
                    case 2 -> location.setX(location.getX() + i);
                }
            } else {
                if (team == Team.RED)
                    location.setZ(location.getZ() - i);
                else
                    location.setZ(location.getZ() + i);
            }

            laneTiles.add(new LaneTile(i, location));
        }

    }


}
