package com.orego.battlecrane.bc.std.race.human.action.build

import com.orego.battlecrane.bc.api.manager.BGameContext
import com.orego.battlecrane.bc.api.manager.mapManager.cell.BCell
import com.orego.battlecrane.bc.api.manager.playerManager.player.BPlayer
import com.orego.battlecrane.bc.api.model.contract.BTargetable
import com.orego.battlecrane.bc.std.race.human.action.BHumanAction
import com.orego.battlecrane.bc.std.race.human.building.implementation.BHumanWall

class BHumanBuildWall(gameContext: BGameContext, owner : BPlayer) : BHumanAction(gameContext, owner), BTargetable {

    override var targetPosition: BCell? = null

    override fun performAction(): Boolean {
        if (this.targetPosition != null && this.owner != null) {
            val wall = BHumanWall(this.gameContext, this.owner!!)
            val mapHolder = this.gameContext.mapManager.mapHolder
            return mapHolder.bindUnitTo(wall, this.targetPosition)
        }
        return false
    }
}