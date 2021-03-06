package com.orego.battlecrane.bc.engine.scenario.skirmish.model.race.human.unit.building.generator.builder

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.util.trigger.unit.BOnDestroyUnitTrigger
import com.orego.battlecrane.bc.engine.api.util.trigger.producable.BOnProduceEnableTrigger
import com.orego.battlecrane.bc.engine.api.util.trigger.turn.BProduceEnableOnTurnTrigger
import com.orego.battlecrane.bc.engine.scenario.skirmish.model.race.human.unit.building.generator.trigger.BSkirmishHumanGeneratorOnLevelActionTrigger
import com.orego.battlecrane.bc.engine.scenario.skirmish.model.race.human.unit.building.generator.trigger.BSkirmishHumanGeneratorOnProduceActionTrigger
import com.orego.battlecrane.bc.engine.scenario.skirmish.util.trigger.BSkirmishOnHitPointsActionTrigger
import com.orego.battlecrane.bc.engine.standardImpl.race.human.unit.building.implementation.BHumanGenerator

class BSkirmishHumanGeneratorBuilder(playerId: Long, x: Int, y: Int) : BHumanGenerator.Builder(playerId, x, y) {

    override fun onCreate(context: BGameContext): BHumanGenerator {
        val generator = super.onCreate(context)
        BSkirmishOnHitPointsActionTrigger.connect(context, generator)
        BOnProduceEnableTrigger.connect(context, generator)
        BOnDestroyUnitTrigger.connect(context, generator)
        BSkirmishHumanGeneratorOnLevelActionTrigger.connect(context, generator)
        BSkirmishHumanGeneratorOnProduceActionTrigger.connect(context, generator)
        BProduceEnableOnTurnTrigger.connect(context, generator)
        return generator
    }
}