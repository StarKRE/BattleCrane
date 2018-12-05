package com.orego.battlecrane.ui.model.std.view.race.human.map.vehicle

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.orego.battlecrane.R
import com.orego.battlecrane.bc.api.model.unit.BUnit
import com.orego.battlecrane.bc.std.race.human.vehicle.implementation.BHumanTank
import com.orego.battlecrane.ui.fragment.battle.render.BRender
import com.orego.battlecrane.ui.model.api.view.map.BUnitView
import com.orego.battlecrane.ui.util.asSimple

class BHumanTankView(unit: BHumanTank, measuredCellSide: Int, context: Context) : BUnitView(unit) {

    override val displayedView: View

    companion object {

        private const val HUMAN_TANK_IMAGE_ID = R.drawable.ic_action_name_2
    }

    init {
        this.displayedView = ImageView(context).asSimple(context, measuredCellSide,
            HUMAN_TANK_IMAGE_ID
        )
    }
    
    class Builder : BRender.ViewBuilder<BUnit, BUnitView> {

        override val type: String = BHumanTank::class.java.name

        override fun build(obj: BUnit, measuredCellSide: Int, context: Context): BUnitView =
            BHumanTankView(obj as BHumanTank, measuredCellSide, context)
    }
}