package com.orego.battlecrane.bc.api.context.pipeline.implementation.unit.node

import com.orego.battlecrane.bc.api.context.BGameContext
import com.orego.battlecrane.bc.api.context.pipeline.model.BEvent
import com.orego.battlecrane.bc.api.context.pipeline.model.BNode
import com.orego.battlecrane.bc.api.context.pipeline.implementation.action.node.pipe.onCreate.BOnCreateActionPipe
import com.orego.battlecrane.bc.api.context.pipeline.implementation.action.node.pipe.onPerform.BOnPerformActionPipe
import com.orego.battlecrane.bc.api.context.pipeline.implementation.unit.BUnitPipe

class BUnitNode(context: BGameContext) : BNode(context) {

    companion object {

        const val NAME = "UNIT_NODE"
    }

    override val name = NAME

    init {
        //Put on sendOnCreateUnitAction turnTimerTask node:
        this.pipeMap[BOnCreateActionPipe.NAME] = BOnCreateActionPipe(context)
        //Put on perform turnTimerTask node:
        this.pipeMap[BOnPerformActionPipe.NAME] = BOnPerformActionPipe(context)
    }

    override fun handle(event: BEvent) : BEvent? {
        return if (event.bundle is BUnitPipe.UnitBundle) {
            this.pipeMap.values.forEach { it.push(event) }
            event
        } else {
            null
        }
    }
}