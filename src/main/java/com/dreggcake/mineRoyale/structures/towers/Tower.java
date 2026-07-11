package com.dreggcake.mineRoyale.structures.towers;

import com.dreggcake.mineRoyale.core.Team;
import com.dreggcake.mineRoyale.structures.towers.Tile;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {

    protected final Team team;
    protected final List<Tile> centerTiles = new ArrayList<>();
    protected final List<Tile> attackTiles = new ArrayList<>();

    protected Tower(Team team) {
        this.team = team;
    }

    protected abstract void calculateTiles();

    public abstract Location getLocation();

    public abstract boolean isKingTower();

    public Team getTeam() {
        return team;
    }

    public List<Tile> getCenterTiles() {
        return centerTiles;
    }

    public List<Tile> getAttackTiles() {
        return attackTiles;
    }
}
