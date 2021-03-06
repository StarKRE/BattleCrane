package com.orego.battlecrane.bc.engine.scenario.skirmish.model.race.human.unit.building.turret.builder

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.util.trigger.unit.BOnDestroyUnitTrigger
import com.orego.battlecrane.bc.engine.scenario.skirmish.model.race.human.unit.building.turret.trigger.BSkirmishHumanTurretOnAttackActionTrigger
import com.orego.battlecrane.bc.engine.scenario.skirmish.model.race.human.unit.building.turret.trigger.BSkirmishHumanTurretOnLevelActionTrigger
import com.orego.battlecrane.bc.engine.scenario.skirmish.model.race.human.unit.building.turret.trigger.BSkirmishHumanTurretOnTurnTrigger
import com.orego.battlecrane.bc.engine.scenario.skirmish.util.trigger.BSkirmishOnHitPointsActionTrigger
import com.orego.battlecrane.bc.engine.standardImpl.race.human.unit.building.implementation.BHumanTurret

class BSkirmishHumanTurretBuilder(playerId: Long, x: Int, y: Int) : BHumanTurret.Builder(playerId, x, y) {

    override fun onCreate(context: BGameContext): BHumanTurret {
        val turret = super.onCreate(context)
        BSkirmishOnHitPointsActionTrigger.connect(context, turret)
        BOnDestroyUnitTrigger.connect(context, turret)
        BSkirmishHumanTurretOnAttackActionTrigger.connect(context, turret)
        BSkirmishHumanTurretOnLevelActionTrigger.connect(context, turret)
        BSkirmishHumanTurretOnTurnTrigger.connect(context, turret)
        return turret
    }
}
