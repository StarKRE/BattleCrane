package com.orego.battlecrane.ui.model.std.view.race.human.map.building

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.orego.battlecrane.R
import com.orego.battlecrane.bc.api.model.unit.BUnit
import com.orego.battlecrane.bc.std.race.human.building.implementation.BHumanTurret
import com.orego.battlecrane.ui.model.api.render.BViewRender
import com.orego.battlecrane.ui.model.api.render.unit.BUnitViewRender
import com.orego.battlecrane.ui.model.api.view.map.BUnitView
import com.orego.battlecrane.ui.util.asSimple

class BHumanTurretView(unit: BHumanTurret, measuredCellSide: Int, context: Context) : BUnitView(unit) {

    override val displayedView: View

    companion object {

        private const val HUMAN_TURRET_IMAGE_ID = R.drawable.ic_action_name_2
    }

    init {
        this.displayedView = ImageView(context)
            .asSimple(context, measuredCellSide, HUMAN_TURRET_IMAGE_ID)
    }

    class Builder : BUnitViewRender.ViewBuilder {

        override val type: String = BHumanTurret::class.java.name

        override fun build(value: BUnit, dimension: Int, context: Context): BUnitView =
            BHumanTurretView(value as BHumanTurret, dimension, context)
    }
}