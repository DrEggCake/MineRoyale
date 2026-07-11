package com.dreggcake.mineRoyale.core;

import com.dreggcake.mineRoyale.MineRoyale;
import com.dreggcake.mineRoyale.structures.towers.KingTower;
import com.dreggcake.mineRoyale.structures.towers.SideTower;
import com.dreggcake.mineRoyale.structures.towers.Tile;
import com.dreggcake.mineRoyale.structures.towers.Tower;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public final class PathFind {

    private enum ArenaSide {
        OWN_SIDE,
        ENEMY_SIDE
    }

    private enum UnitSide {
        LEFT_SIDE,
        RIGHT_SIDE
    }

    public static Vector getDirection(MineRoyale plugin, Unit unit) {
        Arena arena = plugin.getArena();
        Location location = unit.getLocation();
        Team team = unit.getTeam();

        if (getArenaSide(arena, location, team) == ArenaSide.OWN_SIDE) {
            return getOwnSideDirection(arena, location, team);
        }

        return getEnemySideDirection(arena, location, team);
    }

    private static ArenaSide getArenaSide(Arena arena, Location location, Team team) {
        Location ownKing = ownKing(arena, team).getLocation();
        Location enemyKing = enemyKing(arena, team).getLocation();

        if (team == Team.BLUE) {
            return location.distanceSquared(ownKing)
                    < location.distanceSquared(enemyKing)
                    ? ArenaSide.OWN_SIDE
                    : ArenaSide.ENEMY_SIDE;
        }

        return location.distanceSquared(ownKing)
                < location.distanceSquared(enemyKing)
                ? ArenaSide.OWN_SIDE
                : ArenaSide.ENEMY_SIDE;
    }

    private static Vector getOwnSideDirection(Arena arena, Location location, Team team) {
        Waypoint left = ownLeftWaypoint(arena, team);
        Waypoint right = ownRightWaypoint(arena, team);

        UnitSide side = nearestSide(
                location,
                left.getLocation(),
                right.getLocation()
        );

        Location target = side == UnitSide.LEFT_SIDE
                ? left.getLocation()
                : right.getLocation();

        Vector result = direction(location, target);

        Vector next = location.toVector()
                .add(result)
                .toBlockVector();

        result = avoidTower(result, next, ownLeftTower(arena, team));
        result = avoidTower(result, next, ownRightTower(arena, team));
        result = avoidTower(result, next, ownKing(arena, team));

        return result;
    }

    private static Vector getEnemySideDirection(Arena arena, Location location, Team team) {
        SideTower leftTower = enemyLeftTower(arena, team);
        SideTower rightTower = enemyRightTower(arena, team);
        KingTower kingTower = enemyKing(arena, team);

        UnitSide side = nearestSide(
                location,
                leftTower.getLocation(),
                rightTower.getLocation()
        );

        Tower targetTower = side == UnitSide.LEFT_SIDE
                ? leftTower
                : rightTower;

        Location nearest = nearestTile(targetTower, location);

        for (Tile tile : kingTower.getCenterTiles()) {
            if (location.distanceSquared(tile.getLocation())
                    < location.distanceSquared(nearest)) {
                nearest = tile.getLocation();
            }
        }

        return direction(location, nearest);
    }

    private static Vector avoidTower(Vector direction, Vector next, Tower tower) {
        for (Tile tile : tower.getCenterTiles()) {
            if (next.equals(tile.getLocation().toVector().toBlockVector())) {

                UnitSide towerSide;

                if (tower.isKingTower()) {
                    if (next.distanceSquared(tower.getCenterTiles().get(0).getLocation().toVector())
                            < next.distanceSquared(tower.getCenterTiles().get(3).getLocation().toVector())) {
                        towerSide = UnitSide.LEFT_SIDE;
                    } else {
                        towerSide = UnitSide.RIGHT_SIDE;
                    }

                    if (towerSide == UnitSide.LEFT_SIDE) {
                        return direction.clone().setX(1).setZ(0);
                    }

                    return direction.clone().setX(-1).setZ(0);
                }

                if (next.distanceSquared(tower.getCenterTiles().get(0).getLocation().toVector())
                        < next.distanceSquared(tower.getCenterTiles().get(2).getLocation().toVector())) {
                    towerSide = UnitSide.LEFT_SIDE;
                } else {
                    towerSide = UnitSide.RIGHT_SIDE;
                }

                if (towerSide == UnitSide.LEFT_SIDE) {
                    return direction.clone().setX(-1).setZ(0);
                }

                return direction.clone().setX(1).setZ(0);
            }
        }

        return direction;
    }

    private static Location nearestTile(Tower tower, Location location) {
        Location nearest = null;

        for (Tile tile : tower.getCenterTiles()) {
            if (nearest == null ||
                    location.distanceSquared(tile.getLocation())
                            < location.distanceSquared(nearest)) {
                nearest = tile.getLocation();
            }
        }

        return nearest;
    }

    private static UnitSide nearestSide(Location location, Location left, Location right) {
        return location.distanceSquared(left)
                < location.distanceSquared(right)
                ? UnitSide.LEFT_SIDE
                : UnitSide.RIGHT_SIDE;
    }

    private static Vector direction(Location from, Location to) {
        return to.toVector()
                .subtract(from.toVector())
                .normalize();
    }

    private static KingTower ownKing(Arena arena, Team team) {
        return team == Team.BLUE
                ? arena.getBlueKingTower()
                : arena.getRedKingTower();
    }

    private static KingTower enemyKing(Arena arena, Team team) {
        return team == Team.BLUE
                ? arena.getRedKingTower()
                : arena.getBlueKingTower();
    }

    private static SideTower ownLeftTower(Arena arena, Team team) {
        return team == Team.BLUE
                ? arena.getBlueSideTower1()
                : arena.getRedSideTower1();
    }

    private static SideTower ownRightTower(Arena arena, Team team) {
        return team == Team.BLUE
                ? arena.getBlueSideTower2()
                : arena.getRedSideTower2();
    }

    private static SideTower enemyLeftTower(Arena arena, Team team) {
        return team == Team.BLUE
                ? arena.getRedSideTower1()
                : arena.getBlueSideTower1();
    }

    private static SideTower enemyRightTower(Arena arena, Team team) {
        return team == Team.BLUE
                ? arena.getRedSideTower2()
                : arena.getBlueSideTower2();
    }

    private static Waypoint ownLeftWaypoint(Arena arena, Team team) {
        return team == Team.BLUE
                ? arena.getBlueWaypoint1()
                : arena.getRedWaypoint1();
    }

    private static Waypoint ownRightWaypoint(Arena arena, Team team) {
        return team == Team.BLUE
                ? arena.getBlueWaypoint2()
                : arena.getRedWaypoint2();
    }
}