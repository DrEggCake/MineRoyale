package com.dreggcake.mineRoyale.core;

import com.dreggcake.mineRoyale.MineRoyale;
import com.dreggcake.mineRoyale.structures.lane.TowerBridgeLane;
import com.dreggcake.mineRoyale.structures.lane.TowerTowerLane;
import com.dreggcake.mineRoyale.structures.towers.KingTower;
import com.dreggcake.mineRoyale.structures.towers.SideTower;
import org.bukkit.Location;
import org.bukkit.World;

public class Arena {

    private final TowerBridgeLane towerBridgeLane1;
    private final TowerBridgeLane towerBridgeLane2;

    private final TowerTowerLane redTowerTowerLane1;
    private final TowerTowerLane redTowerTowerLane2;

    private final TowerTowerLane blueTowerTowerLane1;
    private final TowerTowerLane blueTowerTowerLane2;

    private final KingTower redKingTower;
    private final SideTower redSideTower1;
    private final SideTower redSideTower2;

    private final KingTower blueKingTower;
    private final SideTower blueSideTower1;
    private final SideTower blueSideTower2;

    MineRoyale plugin;

    public Arena(MineRoyale plugin) {
        this.plugin = plugin;

        World world = plugin.getServer().getWorld("world");

        towerBridgeLane1 = new TowerBridgeLane(new Location(world, 430.5, 92.0, 90.5));
        towerBridgeLane2 = new TowerBridgeLane(new Location(world, 419.5, 92.0, 90.5));

        redTowerTowerLane1 = new TowerTowerLane(1, new Location(world, 427.5, 92.0, 110.5), Team.RED);
        redTowerTowerLane2 = new TowerTowerLane(2, new Location(world, 422.5, 92.0, 110.5), Team.RED);
        blueTowerTowerLane1 = new TowerTowerLane(1, new Location(world, 427.5, 92.0, 85.5), Team.BLUE);
        blueTowerTowerLane2 = new TowerTowerLane(2, new Location(world, 422.5, 92.0, 85.5), Team.BLUE);

        redKingTower = new KingTower(Team.RED, new Location(world, 423.5, 92.0, 112.5));
        redSideTower1 = new SideTower(Team.RED, new Location(world, 419.5, 92.0, 107.5));
        redSideTower2 = new SideTower(Team.RED, new Location(world, 430.5, 92.0, 107.5));

        blueKingTower = new KingTower(Team.BLUE, new Location(world, 426.5, 92.0, 83.5));
        blueSideTower1 = new SideTower(Team.BLUE, new Location(world, 430.5, 92.0, 88.5));
        blueSideTower2 = new SideTower(Team.BLUE, new Location(world, 419.5, 92.0, 88.5));
    }

    public TowerBridgeLane getTowerBridgeLane1() {
        return towerBridgeLane1;
    }

    public TowerBridgeLane getTowerBridgeLane2() {
        return towerBridgeLane2;
    }

    public TowerTowerLane getRedTowerTowerLane1() {
        return redTowerTowerLane1;
    }

    public TowerTowerLane getRedTowerTowerLane2() {
        return redTowerTowerLane2;
    }

    public TowerTowerLane getBlueTowerTowerLane1() {
        return blueTowerTowerLane1;
    }

    public TowerTowerLane getBlueTowerTowerLane2() {
        return blueTowerTowerLane2;
    }

    public KingTower getRedKingTower() {
        return redKingTower;
    }

    public SideTower getRedSideTower1() {
        return redSideTower1;
    }

    public SideTower getRedSideTower2() {
        return redSideTower2;
    }

    public KingTower getBlueKingTower() {
        return blueKingTower;
    }

    public SideTower getBlueSideTower1() {
        return blueSideTower1;
    }

    public SideTower getBlueSideTower2() {
        return blueSideTower2;
    }
}
