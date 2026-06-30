package com.dreggcake.mineRoyale.structures.towers;

import com.dreggcake.mineRoyale.core.Team;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class SideTower {
    private Team team;
    private Location centerLocation; // location of the center block
    private List<Tile> tiles = new ArrayList<>();
    private List<Tile> attackTiles = new ArrayList<>();

    // @formatter:off
    private int[][] CENTER_OFFSETS = {
            {-1, 1}, {0, 1}, {1, 1},
            {-1, 0}, {0, 0}, {1, 0},
            {-1, -1}, {0, -1}, {1, -1}
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
        this.team = team;
        this.centerLocation = centerLocation;

        calculateTiles();
    }

    private void calculateTiles() {
        // center tiles
        for (int i = 0; i < CENTER_OFFSETS.length; i++) {
            int dx = CENTER_OFFSETS[i][0];
            int dz = CENTER_OFFSETS[i][1];
            Location tileLocation = centerLocation.clone().add(dx, 0, dz);
            Tile tile = new Tile(i, tileLocation);
            tiles.add(tile);
        }

        // attack tiles
        for (int i = 0; i < ATTACK_OFFSETS.length; i++) {
            int dx = ATTACK_OFFSETS[i][0];
            int dz = ATTACK_OFFSETS[i][1];

            Location tileLocation = centerLocation.clone().add(dx, 0, dz);
            attackTiles.add(new Tile(i, tileLocation));
        }
    }

    public Team getTeam() {
        return team;
    }

    public List<Tile> getCenterTiles() {
        return tiles;
    }

    public List<Tile> getAttackTiles() {
        return attackTiles;
    }

    public Location getCenterLocation() {
        return centerLocation;
    }
}
