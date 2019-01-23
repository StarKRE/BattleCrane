package com.orego.battlecrane.bc.android.scenario.skirmish.model.race.human.unit.building.wall.builder

import com.orego.battlecrane.bc.android.api.context.BUiGameContext
import com.orego.battlecrane.bc.android.api.holder.unit.trigger.BOnDestroyUnitHolderTrigger
import com.orego.battlecrane.bc.android.scenario.skirmish.model.race.human.unit.building.wall.trigger.BSkirmishHumanWallHolderOnHitPointsActionTrigger
import com.orego.battlecrane.bc.android.standardImpl.race.human.unit.building.BHumanWallHolder
import com.orego.battlecrane.bc.engine.api.model.unit.BUnit

class BSkirmishHumanWallHolderBuilder : BHumanWallHolder.Builder() {

    override fun build(uiGameContext: BUiGameContext, item: BUnit): BHumanWallHolder {
        val holder = super.build(uiGameContext, item)
        BOnDestroyUnitHolderTrigger.connect(uiGameContext, holder)
        BSkirmishHumanWallHolderOnHitPointsActionTrigger.connect(uiGameContext, holder)
        return holder
    }
}