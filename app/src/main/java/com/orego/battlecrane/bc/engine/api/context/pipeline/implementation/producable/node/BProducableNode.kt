package com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.producable.node

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.producable.BProducablePipe
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.producable.node.pipe.onProduceAction.BOnProduceActionPipe
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.producable.node.pipe.onProduceEnable.BOnProduceEnablePipe
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.event.BEvent
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.node.BNode

class BProducableNode(context: BGameContext) : BNode(context) {

    companion object {

        const val NAME = "PRODUCABLE_NODE"
    }

    override val name = NAME

    init {
        this.connectInnerPipe(BOnProduceEnablePipe(context))
        this.connectInnerPipe(BOnProduceActionPipe(context))
    }

    override fun handle(event: BEvent) : BEvent? {
        return if (event is BProducablePipe.Event) {
            this.pushToInnerPipes(event)
            event
        } else {
            null
        }
    }
}