package com.dreggcake.mineRoyale.structures.towers;

import com.dreggcake.mineRoyale.core.Team;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class KingTower {
    private final Team team;
    private final Location bottomLeftLocation; // (0,0) of the 4x4
    private final List<Tile> centerTiles = new ArrayList<>();
    private final List<Tile> attackTiles = new ArrayList<>();

    // @formatter:off
    private static final int[][] CENTER_OFFSETS = {
            {0, 3}, {1, 3}, {2, 3}, {3, 3},
            {0, 2}, {1, 2}, {2, 2}, {3, 2},
            {0, 1}, {1, 1}, {2, 1}, {3, 1},
            {0, 0}, {1, 0}, {2, 0}, {3, 0}
    };

    private static final int[][] ATTACK_OFFSETS = {
            {-1, 4}, {0, 4}, {1, 4}, {2, 4}, {3, 4}, {4, 4},
            {-1, 3},                                 {4, 3},
            {-1, 2},                                 {4, 2},
            {-1, 1},                                 {4, 1},
            {-1, 0},                                 {4, 0},
            {-1,-1}, {0,-1}, {1,-1}, {2,-1}, {3,-1}, {4,-1}
    };
    // @formatter:on

    public KingTower(Team team, Location bottomLeftLocation) {
        this.team = team;
        this.bottomLeftLocation = bottomLeftLocation.clone();

        calculateTiles();
    }

    private void calculateTiles() {
        // Center tiles
        for (int i = 0; i < CENTER_OFFSETS.length; i++) {
            int dx = CENTER_OFFSETS[i][0];
            int dz = CENTER_OFFSETS[i][1];

            Location tileLocation = bottomLeftLocation.clone().add(dx, 0, dz);
            centerTiles.add(new Tile(i, tileLocation));
        }

        // Attack border
        for (int i = 0; i < ATTACK_OFFSETS.length; i++) {
            int dx = ATTACK_OFFSETS[i][0];
            int dz = ATTACK_OFFSETS[i][1];

            Location tileLocation = bottomLeftLocation.clone().add(dx, 0, dz);
            attackTiles.add(new Tile(i, tileLocation));
        }
    }

    public Team getTeam() {
        return team;
    }

    public List<Tile> getCenterTiles() {
        return centerTiles;
    }

    public List<Tile> getAttackTiles() {
        return attackTiles;
    }

    public Location getBottomLeftLocation() {
        return bottomLeftLocation.clone();
    }
}