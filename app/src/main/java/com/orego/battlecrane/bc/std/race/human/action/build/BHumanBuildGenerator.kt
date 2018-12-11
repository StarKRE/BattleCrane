package com.orego.battlecrane.bc.std.race.human.action.build

import com.orego.battlecrane.bc.api.manager.BGameContext
import com.orego.battlecrane.bc.api.manager.mapManager.point.BPoint
import com.orego.battlecrane.bc.api.manager.playerManager.player.BPlayer
import com.orego.battlecrane.bc.api.model.action.BAction
import com.orego.battlecrane.bc.api.model.contract.BTargetable
import com.orego.battlecrane.bc.std.race.human.action.BHumanAction
import com.orego.battlecrane.bc.std.race.human.building.implementation.BHumanGenerator

class BHumanBuildGenerator(gameContext: BGameContext, owner: BPlayer) : BHumanAction(gameContext, owner), BTargetable {

    override var targetPosition: BPoint? = null

    override fun performAction(): Boolean {
        if (this.targetPosition != null && this.owner != null) {
            val generator = BHumanGenerator(this.gameContext, this.owner!!)
            val manager = this.gameContext.mapManager
            return manager.createUnit(generator, this.targetPosition)
        }
        return false
    }

    class Producer(context: BGameContext, owner: BPlayer) : BAction.Producer(context, owner) {

        companion object {

            private const val GENERATOR_LIMIT = 2
        }

        private val unitHeap by lazy { context.mapManager.unitHeap.values }

        override fun produceToStackByAbility(stack: MutableSet<BAction>, abilityCount: Int) {
            if (abilityCount > 0) {
                var generatorCount = 0
                this.unitHeap.forEach { unit ->
                    if (this.owner.owns(unit) && unit is BHumanGenerator) {
                        if (generatorCount == GENERATOR_LIMIT) {
                            return
                        } else {
                            generatorCount++
                        }
                    }
                }
                stack.add(BHumanBuildGenerator(this.context, this.owner))
            }
        }
    }
}