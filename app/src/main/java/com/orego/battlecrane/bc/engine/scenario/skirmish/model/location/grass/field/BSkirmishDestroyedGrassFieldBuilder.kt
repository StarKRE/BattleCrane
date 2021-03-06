package com.orego.battlecrane.bc.engine.scenario.skirmish.model.location.grass.field

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.util.trigger.unit.BOnDestroyUnitTrigger
import com.orego.battlecrane.bc.engine.api.util.trigger.unit.BOnOwnerChangedUnitTrigger
import com.orego.battlecrane.bc.engine.standardImpl.location.grass.field.implementation.BDestroyedGrassField

class BSkirmishDestroyedGrassFieldBuilder(playerId: Long, x: Int, y: Int) : BDestroyedGrassField.Builder(playerId, x, y) {

    override fun onCreate(context: BGameContext): BDestroyedGrassField {
        val destroyedField = super.onCreate(context)
        BOnDestroyUnitTrigger.connect(context, destroyedField)
        BOnOwnerChangedUnitTrigger.connect(context, destroyedField)
        return destroyedField
    }
}