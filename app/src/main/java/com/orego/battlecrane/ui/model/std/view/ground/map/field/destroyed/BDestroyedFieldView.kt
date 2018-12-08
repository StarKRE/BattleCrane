package com.orego.battlecrane.ui.model.std.view.ground.map.field.destroyed

import android.content.Context
import android.widget.ImageView
import com.orego.battlecrane.bc.api.model.unit.BUnit
import com.orego.battlecrane.bc.std.location.grass.field.destroyed.BDestroyedField
import com.orego.battlecrane.R
import com.orego.battlecrane.ui.model.api.render.unit.BUnitViewRender
import com.orego.battlecrane.ui.model.api.view.map.BUnitView
import com.orego.battlecrane.ui.util.asSimple

class BDestroyedFieldView(unit: BDestroyedField, measuredCellSize: Int, context: Context) : BUnitView(unit) {

    companion object {

        private const val IMAGE_ID = R.drawable.std_grass_unit_destroyed_field
    }

    override val displayedView = ImageView(context).asSimple(context, measuredCellSize, IMAGE_ID)

    class Builder : BUnitViewRender.ViewBuilder {

        override val type: String = BDestroyedField::class.java.name

        override fun build(value: BUnit, dimension: Int, context: Context): BUnitView =
            BDestroyedFieldView(value as BDestroyedField, dimension, context)
    }
}