package com.dreggcake.mineRoyale.structures.towers;

import com.dreggcake.mineRoyale.core.Team;
import org.bukkit.Location;

public class KingTower extends Tower {

    private final Location origin;

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

    public KingTower(Team team, Location origin) {
        super(team);
        this.origin = origin.clone();
        calculateTiles();
    }

    @Override
    protected void calculateTiles() {
        int xDir = team == Team.RED ? 1 : -1;
        int zDir = team == Team.RED ? 1 : -1;

        for (int i = 0; i < CENTER_OFFSETS.length; i++) {
            int dx = CENTER_OFFSETS[i][0];
            int dz = CENTER_OFFSETS[i][1];

            centerTiles.add(new Tile(
                    i,
                    origin.clone().add(dx * xDir, 0, dz * zDir)
            ));
        }

        for (int i = 0; i < ATTACK_OFFSETS.length; i++) {
            int dx = ATTACK_OFFSETS[i][0];
            int dz = ATTACK_OFFSETS[i][1];

            attackTiles.add(new Tile(
                    i,
                    origin.clone().add(dx * xDir, 0, dz * zDir)
            ));
        }
    }

    @Override
    public Location getLocation() {
        return origin.clone();
    }

    @Override
    public boolean isKingTower() {
        return true;
    }

    public Location getOrigin() {
        return origin.clone();
    }
}
