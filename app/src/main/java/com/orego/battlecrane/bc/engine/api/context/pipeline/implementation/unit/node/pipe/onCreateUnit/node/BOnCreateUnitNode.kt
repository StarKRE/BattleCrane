package com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.unit.node.pipe.onCreateUnit.node

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.unit.node.pipe.onCreateUnit.BOnCreateUnitPipe
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.event.BEvent
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.node.BNode

class BOnCreateUnitNode(context: BGameContext) : BNode(context) {

    companion object {

        const val NAME = "ON_CREATE_UNIT_NODE"
    }

    override val name =
        NAME

    /**
     * Creates new uiUnit.
     */

    override fun handle(event: BEvent) =
        if (event is BOnCreateUnitPipe.Event) {
            this.pushToInnerPipes(event)
        } else {
            null
        }
}