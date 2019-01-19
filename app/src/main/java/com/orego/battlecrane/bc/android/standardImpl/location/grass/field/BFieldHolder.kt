package com.orego.battlecrane.bc.android.standardImpl.location.grass.field

import android.view.View
import com.orego.battlecrane.bc.engine.standardImpl.location.grass.field.BGrassField
import com.orego.battlecrane.bc.android.api.context.BUiGameContext
import com.orego.battlecrane.bc.android.api.context.clickController.BClickMode
import com.orego.battlecrane.bc.android.api.holder.unit.BUnitHolder

abstract class BFieldHolder(uiGameContext: BUiGameContext, item: BGrassField) :
    BUnitHolder(uiGameContext, item) {

    final override val unitView: View = BUnitHolder.placeImageView(uiGameContext, item, this.getItemPath())

    init {
        this.unitView.setOnClickListener {
            uiGameContext.clickController.pushClickMode(ClickMode())
        }
    }

    protected abstract fun getItemPath() : String

    /**
     * Click mode.
     */

    inner class ClickMode : BUnitHolder.ClickMode(this) {

        override fun handle(nextClickMode: BClickMode) = nextClickMode
    }
}