package com.dreggcake.mineRoyale.core;

import com.dreggcake.mineRoyale.MineRoyale;
import com.dreggcake.mineRoyale.structures.lane.TowerBridgeLane;
import com.dreggcake.mineRoyale.structures.lane.TowerTowerLane;
import com.dreggcake.mineRoyale.structures.towers.KingTower;
import com.dreggcake.mineRoyale.structures.towers.SideTower;
import com.dreggcake.mineRoyale.structures.towers.Tile;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public final class PathFind {

    enum ArenaSide {
        OWN_SIDE,
        ENEMY_SIDE
    }

    enum UnitSide {
        LEFT_SIDE,
        RIGHT_SIDE
    }

    public static Vector getDirection(MineRoyale plugin, Unit unit) {
        Vector result;
        ArenaSide arenaSide;
        UnitManager unitManager = plugin.getUnitManager();
        Arena arena = plugin.getArena();
        KingTower redKingTower = arena.getRedKingTower();
        SideTower redSideTower1 = arena.getRedSideTower1();
        SideTower redSideTower2 = arena.getRedSideTower2();
        TowerTowerLane redTowerTowerLane1 = arena.getRedTowerTowerLane1();
        TowerTowerLane redTowerTowerLane2 = arena.getRedTowerTowerLane2();
        Waypoint redWaypoint1 = arena.getRedWaypoint1();
        Waypoint redWaypoint2 = arena.getRedWaypoint2();
        KingTower blueKingTower = arena.getBlueKingTower();
        SideTower blueSideTower1 = arena.getBlueSideTower1();
        SideTower blueSideTower2 = arena.getBlueSideTower2();
        TowerTowerLane blueTowerTowerLane1 = arena.getBlueTowerTowerLane1();
        TowerTowerLane blueTowerTowerLane2 = arena.getBlueTowerTowerLane2();
        Waypoint blueWaypoint1 = arena.getBlueWaypoint1();
        Waypoint blueWaypoint2 = arena.getBlueWaypoint2();
        TowerBridgeLane towerBridgeLane1 = arena.getTowerBridgeLane1();
        TowerBridgeLane towerBridgeLane2 = arena.getTowerBridgeLane2();
        Location location = unit.getLocation();
        Team team = unit.getTeam();

        if (team == Team.BLUE) {
            if (location.distanceSquared(blueKingTower.getBottomLeftLocation())
                    < unit.getLocation().distanceSquared(redKingTower.getBottomLeftLocation()))
                arenaSide = ArenaSide.OWN_SIDE;
            else arenaSide = ArenaSide.ENEMY_SIDE;
        } else {
            if (location.distanceSquared(redKingTower.getBottomLeftLocation())
                    < unit.getLocation().distanceSquared(blueKingTower.getBottomLeftLocation()))
                arenaSide = ArenaSide.ENEMY_SIDE;
            else arenaSide = ArenaSide.OWN_SIDE;
        }

        if (arenaSide == ArenaSide.OWN_SIDE) {
            UnitSide unitSide;
            if (team == Team.BLUE) {
                if (location.distanceSquared(blueWaypoint1.getLocation()) < location.distanceSquared(blueWaypoint2.getLocation()))
                    unitSide = UnitSide.LEFT_SIDE;
                else unitSide = UnitSide.RIGHT_SIDE;
                if (unitSide == UnitSide.LEFT_SIDE)
                    result = blueWaypoint1.getLocation().toVector().subtract(location.toVector()).normalize();
                else result = blueWaypoint2.getLocation().toVector().subtract(location.toVector()).normalize();
            } else {
                if (location.distanceSquared(redWaypoint1.getLocation()) < location.distanceSquared(redWaypoint2.getLocation()))
                    unitSide = UnitSide.LEFT_SIDE;
                else unitSide = UnitSide.RIGHT_SIDE;
                if (unitSide == UnitSide.LEFT_SIDE)
                    result = redWaypoint1.getLocation().toVector().subtract(location.toVector()).normalize();
                else result = redWaypoint2.getLocation().toVector().subtract(location.toVector()).normalize();
            }

            Vector next = location.toVector().add(result).toBlockVector();

            if (team == Team.BLUE) {
                for (Tile tile : blueSideTower1.getCenterTiles()) {
                    if (next.equals(tile.getLocation().toVector().toBlockVector())) {
                        UnitSide towerSide;
                        if (next.distanceSquared(blueSideTower1.getCenterTiles().get(0).getLocation().toVector())
                                < next.distanceSquared(blueSideTower1.getCenterTiles().get(2).getLocation().toVector()))
                            towerSide = UnitSide.LEFT_SIDE;
                        else towerSide = UnitSide.RIGHT_SIDE;
                        if (towerSide == UnitSide.LEFT_SIDE) {
                            result = result.clone().setX(-1).setZ(0);
                        } else result = result.clone().setX(1).setZ(0);
                    }
                }
                for (Tile tile : blueSideTower2.getCenterTiles()) {
                    if (next.equals(tile.getLocation().toVector().toBlockVector())) {
                        UnitSide towerSide;
                        if (next.distanceSquared(blueSideTower2.getCenterTiles().get(0).getLocation().toVector())
                                < next.distanceSquared(blueSideTower2.getCenterTiles().get(2).getLocation().toVector()))
                            towerSide = UnitSide.LEFT_SIDE;
                        else towerSide = UnitSide.RIGHT_SIDE;
                        if (towerSide == UnitSide.LEFT_SIDE) {
                            result = result.clone().setX(-1).setZ(0);
                        } else result = result.clone().setX(1).setZ(0);
                    }
                }
                for (Tile tile : blueKingTower.getCenterTiles()) {
                    if (next.equals(tile.getLocation().toVector().toBlockVector())) {
                        UnitSide towerSide;
                        if (next.distanceSquared(blueKingTower.getCenterTiles().get(0).getLocation().toVector())
                                < next.distanceSquared(blueKingTower.getCenterTiles().get(3).getLocation().toVector()))
                            towerSide = UnitSide.LEFT_SIDE;
                        else towerSide = UnitSide.RIGHT_SIDE;
                        if (towerSide == UnitSide.LEFT_SIDE) {
                            result = result.clone().setX(-1).setZ(0);
                        } else result = result.clone().setX(1).setZ(0);
                    }
                }
            } else {
                for (Tile tile : redSideTower1.getCenterTiles()) {
                    if (next.equals(tile.getLocation().toVector().toBlockVector())) {
                        UnitSide towerSide;
                        if (next.distanceSquared(redSideTower1.getCenterTiles().get(0).getLocation().toVector())
                                < next.distanceSquared(redSideTower1.getCenterTiles().get(2).getLocation().toVector()))
                            towerSide = UnitSide.LEFT_SIDE;
                        else towerSide = UnitSide.RIGHT_SIDE;
                        if (towerSide == UnitSide.LEFT_SIDE) {
                            result = result.clone().setX(-1).setZ(0);
                        } else result = result.clone().setX(1).setZ(0);
                    }
                }
                for (Tile tile : redSideTower2.getCenterTiles()) {
                    if (next.equals(tile.getLocation().toVector().toBlockVector())) {
                        UnitSide towerSide;
                        if (next.distanceSquared(redSideTower2.getCenterTiles().get(0).getLocation().toVector())
                                < next.distanceSquared(redSideTower2.getCenterTiles().get(2).getLocation().toVector()))
                            towerSide = UnitSide.LEFT_SIDE;
                        else towerSide = UnitSide.RIGHT_SIDE;
                        if (towerSide == UnitSide.LEFT_SIDE) {
                            result = result.clone().setX(-1).setZ(0);
                        } else result = result.clone().setX(1).setZ(0);
                    }
                }
                for (Tile tile : redKingTower.getCenterTiles()) {
                    if (next.equals(tile.getLocation().toVector().toBlockVector())) {
                        UnitSide towerSide;
                        if (next.distanceSquared(redKingTower.getCenterTiles().get(0).getLocation().toVector())
                                < next.distanceSquared(redKingTower.getCenterTiles().get(3).getLocation().toVector()))
                            towerSide = UnitSide.LEFT_SIDE;
                        else towerSide = UnitSide.RIGHT_SIDE;
                        if (towerSide == UnitSide.LEFT_SIDE) {
                            result = result.clone().setX(-1).setZ(0);
                        } else result = result.clone().setX(1).setZ(0);
                    }
                }
            }
        } else {
            UnitSide unitSide;
            if (team == Team.BLUE) {
                if (location.distanceSquared(redSideTower1.getCenterLocation()) < location.distanceSquared(redSideTower2.getCenterLocation()))
                    unitSide = UnitSide.LEFT_SIDE;
                else unitSide = UnitSide.RIGHT_SIDE;
            } else {
                if (location.distanceSquared(blueSideTower1.getCenterLocation()) < location.distanceSquared(blueSideTower2.getCenterLocation()))
                    unitSide = UnitSide.LEFT_SIDE;
                else unitSide = UnitSide.RIGHT_SIDE;
            }

            Location nearest = null;
            if (unitSide == UnitSide.LEFT_SIDE) {
                if (team == Team.BLUE) {
                    for (Tile tile : redSideTower1.getCenterTiles()) {
                        if (nearest == null || unit.getLocation().distanceSquared(tile.getLocation()) < unit.getLocation().distanceSquared(nearest)) {
                            nearest = tile.getLocation();
                        }
                    }
                } else {
                    for (Tile tile : blueSideTower1.getCenterTiles()) {
                        if (nearest == null || unit.getLocation().distanceSquared(tile.getLocation()) < unit.getLocation().distanceSquared(nearest)) {
                            nearest = tile.getLocation();
                        }
                    }
                }
            } else {
                if (team == Team.BLUE) {
                    for (Tile tile : redSideTower2.getCenterTiles()) {
                        if (nearest == null || unit.getLocation().distanceSquared(tile.getLocation()) < unit.getLocation().distanceSquared(nearest)) {
                            nearest = tile.getLocation();
                        }
                    }
                } else {
                    for (Tile tile : blueSideTower2.getCenterTiles()) {
                        if (nearest == null || unit.getLocation().distanceSquared(tile.getLocation()) < unit.getLocation().distanceSquared(nearest)) {
                            nearest = tile.getLocation();
                        }
                    }
                }
            }

            Location newNearest = nearest;
            if (team == Team.BLUE) {
                for (Tile tile : redKingTower.getCenterTiles()) {
                    if (unit.getLocation().distanceSquared(tile.getLocation()) < unit.getLocation().distanceSquared(newNearest)) {
                        newNearest = tile.getLocation();
                    }
                }
            } else {
                for (Tile tile : blueKingTower.getCenterTiles()) {
                    if (unit.getLocation().distanceSquared(tile.getLocation()) < unit.getLocation().distanceSquared(newNearest)) {
                        newNearest = tile.getLocation();
                    }
                }
            }

            result = newNearest.toVector().subtract(location.toVector()).normalize();
        }

        return result;
    }
}