package com.orego.battlecrane.bc.engine.scenario.skirmish.model.race.human.trigger.building

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.context.pipeline.implementation.unit.node.pipe.onDestroyUnit.BOnDestroyUnitPipe
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.event.BEvent
import com.orego.battlecrane.bc.engine.api.model.unit.BUnit
import com.orego.battlecrane.bc.engine.api.util.trigger.unit.BOnCreateUnitTrigger
import com.orego.battlecrane.bc.engine.scenario.skirmish.model.race.human.unit.building.wall.builder.BSkirmishHumanWallBuilder

class BSkirmishHumanWallOnCreateTrigger private constructor(context: BGameContext, playerId: Long) :
    BOnCreateUnitTrigger(context, playerId) {

    override fun handle(event: BEvent): BEvent? {
        if (event is Event && event.playerId == this.playerId) {
            //Get context:
            val controller = context.mapController
            val pipeline = context.pipeline
            val storage = this.context.storage
            //Get position:
            val x = event.x
            val y = event.y
            val nextY = y + NEXT_WALL_POSITION
            //Get previous units:
            val unitId1 = controller[x, y]
            val unitId2 = controller[x, nextY]
            //Delete previous units:
            pipeline.pushEvent(BOnDestroyUnitPipe.Event(unitId1))
            pipeline.pushEvent(BOnDestroyUnitPipe.Event(unitId2))
            //Create walls:
            val wall1 = BSkirmishHumanWallBuilder(this.playerId, x, y).build(this.context)
            val wall2 = BSkirmishHumanWallBuilder(this.playerId, x, nextY).build(this.context)
            //Set walls:
            controller.notifyUnitChanged(wall1)
            controller.notifyUnitChanged(wall2)
            storage.putObject(wall1)
            storage.putObject(wall2)
            return this.pushToInnerPipes(event)
        }
        return null
    }

    override fun intoPipe() = Pipe()

    /**
     * Pipe.
     */

    inner class Pipe : BOnCreateUnitTrigger.Pipe()

    /**
     * Event.
     */

    class Event(playerId: Long, x: Int, y: Int) : BOnCreateUnitTrigger.Event(playerId, x, y) {

        override val width: Int
            get() {
                throw IllegalStateException("The trigger handles this event manually!")
            }

        override val height: Int
            get() {
                throw IllegalStateException("The trigger handles this event manually!")
            }

        override fun perform(context: BGameContext): Boolean {
            throw IllegalStateException("The trigger handles this event manually!")
        }

        override fun createUnit(context: BGameContext): BUnit {
            throw IllegalStateException("The trigger handles this event manually!")
        }
    }

    companion object {

        const val NEXT_WALL_POSITION = 1

        fun connect(context: BGameContext, playerId: Long) {
            BOnCreateUnitTrigger.connect(context) {
                BSkirmishHumanWallOnCreateTrigger(
                    context,
                    playerId
                )
            }
        }
    }
}