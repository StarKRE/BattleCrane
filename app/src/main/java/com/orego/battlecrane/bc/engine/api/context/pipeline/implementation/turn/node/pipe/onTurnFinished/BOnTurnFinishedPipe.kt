package com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.turn.node.pipe.onTurnFinished

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.turn.BTurnPipe
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.turn.node.pipe.onTurnFinished.node.BOnTurnFinishedNode
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.pipe.BPipe

class BOnTurnFinishedPipe(context: BGameContext) : BPipe(context) {

    companion object {

        const val NAME = "ON_TURN_FINISHED_PIPE"
    }

    override val name = NAME

    init {
        this.placeNode(BOnTurnFinishedNode(context))
    }

    open class Event(playerId: Long) : BTurnPipe.Event(playerId)
}