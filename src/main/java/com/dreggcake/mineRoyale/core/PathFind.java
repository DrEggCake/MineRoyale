package com.dreggcake.mineRoyale.core;

import com.dreggcake.mineRoyale.MineRoyale;
import com.dreggcake.mineRoyale.structures.lane.TowerBridgeLane;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public final class PathFind {
    public static Vector getDirection(MineRoyale plugin, Unit unit) {
        Arena arena = plugin.getArena();
        Location unitLocation = unit.getLivingEntity().getLocation();

        TowerBridgeLane lane1 = arena.getTowerBridgeLane1();
        TowerBridgeLane lane2 = arena.getTowerBridgeLane2();

        Location lane1Start = lane1.getLaneTiles().getFirst().getLocation();
        Location lane2Start = lane2.getLaneTiles().getFirst().getLocation();

        TowerBridgeLane chosenLane =
                unitLocation.distanceSquared(lane1Start) <
                        unitLocation.distanceSquared(lane2Start)
                        ? lane1
                        : lane2;

        Location target;
        double offset = 0.4; // blocks before the bridge

        switch (unit.getTeam()) {
            case BLUE -> {
                Location bridgeTile = chosenLane.getLaneTiles().get(6).getLocation().clone();
                Location previousTile = chosenLane.getLaneTiles().get(5).getLocation();

                Vector offsetDir = previousTile.toVector()
                        .subtract(bridgeTile.toVector())
                        .normalize();

                target = bridgeTile.add(offsetDir.multiply(offset));
            }

            case RED -> {
                Location bridgeTile = chosenLane.getLaneTiles().get(9).getLocation().clone();
                Location previousTile = chosenLane.getLaneTiles().get(10).getLocation();

                Vector offsetDir = previousTile.toVector()
                        .subtract(bridgeTile.toVector())
                        .normalize();

                target = bridgeTile.add(offsetDir.multiply(offset));
            }

            default -> {
                return new Vector();
            }
        }

        return target.toVector()
                .subtract(unitLocation.toVector())
                .normalize();
    }
}