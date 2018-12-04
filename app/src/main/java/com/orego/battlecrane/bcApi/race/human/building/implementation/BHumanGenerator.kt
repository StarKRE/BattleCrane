package com.orego.battlecrane.bcApi.race.human.building.implementation

import com.orego.battlecrane.bcApi.manager.BGameContext
import com.orego.battlecrane.bcApi.model.unit.BUnit
import com.orego.battlecrane.bcApi.model.unit.contract.BHealthable
import com.orego.battlecrane.bcApi.model.unit.contract.BLevelable
import com.orego.battlecrane.bcApi.race.human.building.BHumanBuilding

class BHumanGenerator(gameContext: BGameContext) : BUnit(gameContext),
    BHumanBuilding, BHealthable, BLevelable {

    companion object {

        private const val DEFAULT_VERTICAL_SIDE = 2

        private const val DEFAULT_HORIZONTAL_SIDE = 2

        private const val DEFAULT_MAX_HEALTH = 1

        private const val DEFAULT_LEVEL = 1

        private const val DEFAULT_MAX_LEVEL = 3
    }

    override val verticalSide =
        DEFAULT_VERTICAL_SIDE

    override val horizontalSide =
        DEFAULT_HORIZONTAL_SIDE

    override var currentHealth =
        DEFAULT_MAX_HEALTH

    override var maxHealth =
        DEFAULT_MAX_HEALTH

    override var currentLevel =
        DEFAULT_LEVEL

    override var maxLevel =
        DEFAULT_MAX_LEVEL

    override val decreaseHealthObserver: MutableMap<Long, BHealthable.HealthListener> = mutableMapOf()

    override val increaseHealthObserver: MutableMap<Long, BHealthable.HealthListener> = mutableMapOf()

    override val levelUpObserver: MutableMap<Long, BLevelable.LevelListener> = mutableMapOf()

    override val levelDownObserver: MutableMap<Long, BLevelable.LevelListener> = mutableMapOf()
}