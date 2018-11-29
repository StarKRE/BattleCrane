package com.orego.battlecrane.bcApi.scenario.skirmish;

import com.orego.battlecrane.bcApi.manager.battlefield.BBattleMap;
import com.orego.battlecrane.bcApi.manager.unit.BUnit;
import com.orego.battlecrane.bcApi.manager.unit.field.BField;
import com.orego.battlecrane.bcApi.manager.unit.field.empty.BEmptyField;
import com.orego.battlecrane.bcApi.scenario.BGameScenario;

import static com.orego.battlecrane.bcApi.manager.battlefield.BBattleMap.MAP_SIDE;

public final class BSkirmishScenario implements BGameScenario {

    @Override
    public final void initMap(final BBattleMap.BMapHolder mapHolder) {
        for (int x = 0; x < MAP_SIDE; x++) {
            for (int y = 0; y < MAP_SIDE; y++) {
                final BUnit emptyField = new BEmptyField();
                mapHolder.bindUnitTo(emptyField, x, y);
            }
        }
    }
}