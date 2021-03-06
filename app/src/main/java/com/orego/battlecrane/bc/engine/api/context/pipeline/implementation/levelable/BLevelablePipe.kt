package com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.levelable

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.levelable.node.BLevelableNode
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.event.BEvent
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.pipe.BPipe

/**
 * Passes all level events.
 */

class BLevelablePipe(context: BGameContext) : BPipe(context) {

    companion object {

        const val NAME = "LEVELABLE_PIPE"
    }

    override val name = NAME

    init{
        this.placeNode(BLevelableNode(context))
    }

    /**
     * Level event.
     */

    open class Event : BEvent()
}