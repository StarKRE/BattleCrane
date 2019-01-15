package com.orego.battlecrane.bc.std.scenario.skirmish.model.race.human.unit.building.factory.trigger

import com.orego.battlecrane.bc.api.context.BGameContext
import com.orego.battlecrane.bc.api.context.pipeline.implementation.levelable.node.pipe.onLevelAction.node.BOnLevelActionNode
import com.orego.battlecrane.bc.api.context.pipeline.implementation.producable.node.pipe.onProduceEnable.BOnProduceEnablePipe
import com.orego.battlecrane.bc.api.context.pipeline.implementation.turn.BTurnPipe
import com.orego.battlecrane.bc.api.context.pipeline.implementation.turn.node.pipe.onTurnFinished.BOnTurnFinishedPipe
import com.orego.battlecrane.bc.api.context.pipeline.implementation.turn.node.pipe.onTurnStarted.BOnTurnStartedPipe
import com.orego.battlecrane.bc.api.context.pipeline.model.event.BEvent
import com.orego.battlecrane.bc.api.context.pipeline.model.node.BNode
import com.orego.battlecrane.bc.std.race.human.unit.building.implementation.BHumanFactory

class BSkirmishHumanFactoryOnTurnTrigger private constructor(context: BGameContext, var factory: BHumanFactory) :
    BNode(context) {

    /**
     * Context.
     */

    private val pipeline = context.pipeline

    override fun handle(event: BEvent): BEvent? {
        if (event is BTurnPipe.Event && this.factory.playerId == event.playerId) {
            val producableId = this.factory.producableId
            when (event) {
                is BOnTurnStartedPipe.Event -> {
                    this.pushToInnerPipes(event)
                    this.pipeline.pushEvent(
                        BOnProduceEnablePipe.createEvent(producableId, true)
                    )
                    return event
                }
                is BOnTurnFinishedPipe.Event -> {
                    this.pushToInnerPipes(event)
                    this.pipeline.pushEvent(
                        BOnProduceEnablePipe.createEvent(producableId, false)
                    )
                    return event
                }
            }
        }
        return null
    }

    companion object {

        fun connect(context: BGameContext, factory: BHumanFactory) {
            BOnLevelActionNode.connect(context) {
                BSkirmishHumanFactoryOnTurnTrigger(
                    context,
                    factory
                )
            }
        }
    }
}