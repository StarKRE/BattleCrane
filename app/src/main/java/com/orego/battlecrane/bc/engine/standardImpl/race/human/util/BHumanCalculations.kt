package com.orego.battlecrane.bc.engine.standardImpl.race.human.util

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.context.storage.heap.implementation.BUnitHeap
import com.orego.battlecrane.bc.engine.api.model.util.BLevelable
import com.orego.battlecrane.bc.engine.standardImpl.race.human.unit.building.BHumanBuilding
import com.orego.battlecrane.bc.engine.standardImpl.race.human.unit.building.implementation.BHumanBarracks
import com.orego.battlecrane.bc.engine.standardImpl.race.human.unit.building.implementation.BHumanFactory
import com.orego.battlecrane.bc.engine.standardImpl.race.human.unit.building.implementation.BHumanGenerator

object BHumanCalculations {

    fun countDiffBarracksFactory(context: BGameContext, playerId: Long): Int {
        val allUnits = context.storage.getHeap(BUnitHeap::class.java).getObjectList()
        var barracksCount = 0
        var factoryCount = 0
        for (unit in allUnits) {
            if (unit.playerId == playerId) {
                when (unit) {
                    is BHumanBarracks -> barracksCount++
                    is BHumanFactory -> factoryCount++
                }
            }
        }
        return barracksCount - factoryCount
    }

    fun countGenerators(context: BGameContext, playerId: Long): Int {
        val allUnits = context.storage.getHeap(BUnitHeap::class.java).getObjectList()
        var generatorCount = 0
        for (unit in allUnits) {
            if (unit is BHumanGenerator && unit.playerId == playerId) {
                generatorCount++
            }
        }
        return generatorCount
    }

    fun countPossibleBuildingUpgrades(context: BGameContext, playerId: Long) : Int {
        val allUnits = context.storage.getHeap(BUnitHeap::class.java).getObjectList()
        var buildingUpgradeCount = 0
        for (unit in allUnits) {
            if (unit is BHumanBuilding
                && unit is BLevelable
                && unit.currentLevel < unit.maxLevel
                && unit.playerId == playerId) {
                buildingUpgradeCount++
            }
        }
        return buildingUpgradeCount
    }
}