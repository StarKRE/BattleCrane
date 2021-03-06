package com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.unit.node

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.unit.BUnitPipe
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.unit.node.pipe.onCreateUnit.BOnCreateUnitPipe
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.unit.node.pipe.onDestroyUnit.BOnDestroyUnitPipe
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.unit.node.pipe.onOwnerChanged.BOnOwnerChangedUnitPipe
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.event.BEvent
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.node.BNode

class BUnitNode(context: BGameContext) : BNode(context) {

    companion object {

        const val NAME = "UNIT_NODE"
    }

    override val name = NAME

    init {
        this.connectInnerPipe(BOnCreateUnitPipe(context))
        this.connectInnerPipe(BOnDestroyUnitPipe(context))
        this.connectInnerPipe(BOnOwnerChangedUnitPipe(context))
    }

    override fun handle(event: BEvent) =
        if (event is BUnitPipe.Event) {
            this.pushToInnerPipes(event)
        } else {
            null
        }
}