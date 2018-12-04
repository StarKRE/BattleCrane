package com.orego.battlecrane.ui.fragment.battle.render.tool.unit.implementations

import com.orego.battlecrane.bcApi.manager.playerManager.BPlayerManager
import com.orego.battlecrane.bcApi.model.unit.BUnit
import com.orego.battlecrane.ui.fragment.battle.render.tool.unit.BUnitToolRender

class BTrainToolRender(private val playerManager: BPlayerManager) : BUnitToolRender() {

    override val stack: List<Class<out BUnit>>
        get() = this.playerManager.currentPlayer.tools.armyStack
}