package com.dreggcake.mineRoyale;

import java.util.ArrayList;
import java.util.List;

public class UnitManager {

    private List<Unit> units = new ArrayList<>();

    public void add(Unit unit) {
        units.add(unit);
    }

    public void tick() {
        for (Unit unit : units) {
            unit.update();
        }
    }
}
