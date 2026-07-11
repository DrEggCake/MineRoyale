package com.dreggcake.mineRoyale.structures.towers;

import com.dreggcake.mineRoyale.core.Team;
import org.bukkit.Location;

public class SideTower extends Tower {

    private final Location centerLocation;

    // @formatter:off
    private static final int[][] CENTER_OFFSETS = {
            {-1, 1}, {0, 1}, {1, 1},
            {-1, 0}, {0, 0}, {1, 0},
            {-1,-1}, {0,-1}, {1,-1}
    };

    private static final int[][] ATTACK_OFFSETS = {
            {-2,  2}, {-1,  2}, { 0,  2}, { 1,  2}, { 2,  2},
            {-2,  1},                               { 2,  1},
            {-2,  0},                               { 2,  0},
            {-2, -1},                               { 2, -1},
            {-2, -2}, {-1, -2}, { 0, -2}, { 1, -2}, { 2, -2}
    };
    // @formatter:on

    public SideTower(Team team, Location centerLocation) {
        super(team);
        this.centerLocation = centerLocation.clone();
        calculateTiles();
    }

    @Override
    protected void calculateTiles() {
        for (int i = 0; i < CENTER_OFFSETS.length; i++) {
            int dx = CENTER_OFFSETS[i][0];
            int dz = CENTER_OFFSETS[i][1];

            centerTiles.add(new Tile(
                    i,
                    centerLocation.clone().add(dx, 0, dz)
            ));
        }

        for (int i = 0; i < ATTACK_OFFSETS.length; i++) {
            int dx = ATTACK_OFFSETS[i][0];
            int dz = ATTACK_OFFSETS[i][1];

            attackTiles.add(new Tile(
                    i,
                    centerLocation.clone().add(dx, 0, dz)
            ));
        }
    }

    @Override
    public Location getLocation() {
        return centerLocation.clone();
    }

    @Override
    public boolean isKingTower() {
        return false;
    }

    public Location getCenterLocation() {
        return centerLocation.clone();
    }
}
