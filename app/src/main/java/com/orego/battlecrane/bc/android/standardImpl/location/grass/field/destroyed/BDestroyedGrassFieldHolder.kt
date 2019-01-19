package com.orego.battlecrane.bc.android.standardImpl.location.grass.field.destroyed

import com.orego.battlecrane.bc.engine.api.model.unit.BUnit
import com.orego.battlecrane.bc.engine.standardImpl.location.grass.field.implementation.BDestroyedGrassField
import com.orego.battlecrane.bc.android.api.context.BUiGameContext
import com.orego.battlecrane.bc.android.api.holder.unit.BUnitHolder
import com.orego.battlecrane.bc.android.standardImpl.location.grass.field.BFieldHolder

class BDestroyedGrassFieldHolder(uiGameContext: BUiGameContext, override val  item: BDestroyedGrassField) :
    BFieldHolder(uiGameContext, item) {

    companion object {

        private const val PATH = "std/grass/unit/destroyed_field.png"
    }

    override fun getItemPath() = PATH

    /**
     * Builder.
     */

    open class Builder : BUnitHolder.Builder() {

        override fun build(uiGameContext: BUiGameContext, item: BUnit): BUnitHolder =
            BDestroyedGrassFieldHolder(uiGameContext, item as BDestroyedGrassField)
    }
}