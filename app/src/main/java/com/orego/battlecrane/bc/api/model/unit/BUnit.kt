package com.orego.battlecrane.bc.api.model.unit

import com.orego.battlecrane.bc.api.manager.BGameContext
import com.orego.battlecrane.bc.api.manager.mapManager.cell.BCell
import com.orego.battlecrane.bc.api.manager.playerManager.player.BPlayer
import com.orego.battlecrane.bc.api.util.BIdGenerator

abstract class BUnit(
    protected val gameContext : BGameContext,
    var owner : BPlayer? = null
) {

    val unitId = BIdGenerator.generateUnitId()

    var pivot: BCell? = null

    abstract val verticalSide: Int

    abstract val horizontalSide: Int

    val onCreateObserver = mutableMapOf<Long, OnCreateListener>()

    val onDestroyListener = mutableMapOf<Long, OnDestroyListener>()

    //TODO: Can place?

    interface OnCreateListener {

        fun onCreate(unit : BUnit)
    }

    interface OnDestroyListener {

        fun onDestroy(unit: BUnit)
    }
}