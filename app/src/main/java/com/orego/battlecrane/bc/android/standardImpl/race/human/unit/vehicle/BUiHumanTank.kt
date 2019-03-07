package com.orego.battlecrane.bc.android.standardImpl.race.human.unit.vehicle

import com.orego.battlecrane.bc.android.api.context.BUiGameContext
import com.orego.battlecrane.bc.android.api.model.unit.BUiUnit
import com.orego.battlecrane.bc.android.standardImpl.race.human.asset.BUiHumanAssets
import com.orego.battlecrane.bc.android.standardImpl.race.human.unit.building.BUiHumanBarracks
import com.orego.battlecrane.bc.engine.standardImpl.race.human.unit.vehicle.implementation.BHumanTank

class BUiHumanTank private constructor(uiGameContext: BUiGameContext, override val unit: BHumanTank) :
    BUiUnit(uiGameContext, unit) {

    companion object {

        const val PATH = "${BUiHumanAssets.Unit.Vehicle.PATH}/tank"
    }

    override fun getAssetPath(): String {
        val viewKey = this.viewMode.key
        val hitPoints = this.unit.currentHitPoints
        return "$PATH/$viewKey/$hitPoints.png"
    }

    /**
     * Builder.
     */

    open class Builder(override val unit: BHumanTank) : BUiUnit.Builder(unit) {

        override fun onCreate(uiGameContext: BUiGameContext) =
            BUiHumanTank(uiGameContext, this.unit)
    }
}