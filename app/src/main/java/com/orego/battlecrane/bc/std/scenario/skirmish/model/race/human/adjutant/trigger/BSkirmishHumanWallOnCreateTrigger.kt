package com.orego.battlecrane.bc.std.scenario.skirmish.model.race.human.adjutant.trigger

import com.orego.battlecrane.bc.api.context.BGameContext
import com.orego.battlecrane.bc.api.context.pipeline.implementation.attackable.node.pipe.onAttackAction.BOnAttackActionPipe
import com.orego.battlecrane.bc.api.context.pipeline.implementation.unit.node.pipe.onDestroyUnit.BOnDestroyUnitPipe
import com.orego.battlecrane.bc.api.context.pipeline.model.component.adjutant.BAdjutantComponent
import com.orego.battlecrane.bc.api.context.pipeline.model.event.BEvent
import com.orego.battlecrane.bc.api.model.adjutant.trigger.BUnitOnCreateTrigger
import com.orego.battlecrane.bc.api.model.entity.main.unit.BUnit
import com.orego.battlecrane.bc.std.race.human.unit.building.implementation.wall.BHumanWall

@BAdjutantComponent
class BSkirmishHumanWallOnCreateTrigger private constructor(context: BGameContext, playerId: Long) :
    BUnitOnCreateTrigger(context, playerId) {

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
            //Create walls:
            val wall1 = BSkirmishHumanWallBuilder().build(this.context, this.playerId, x, y)
            val wall2 = BSkirmishHumanWallBuilder().build(this.context, this.playerId, x, nextY)
            //Get previous units:
            val unitId1 = controller.getUnitIdByPosition(x, y)
            val unitId2 = controller.getUnitIdByPosition(x, nextY)
            //Set walls:
            controller.placeUnitOnMap(wall1)
            controller.placeUnitOnMap(wall2)
            storage.addObject(wall1)
            storage.addObject(wall1)
            //Delete previous units:
            pipeline.pushEvent(BOnDestroyUnitPipe.createEvent(unitId1))
            pipeline.pushEvent(BOnDestroyUnitPipe.createEvent(unitId2))
            return this.pushToInnerPipes(event)
        }
        return null
    }

    override fun intoPipe() = Pipe()

    /**
     * Pipe.
     */

    inner class Pipe : BUnitOnCreateTrigger.Pipe()

    /**
     * Event.
     */

    class Event private constructor(playerId: Long, x: Int, y: Int) : BUnitOnCreateTrigger.Event(playerId, x, y) {

        override fun perform(context: BGameContext): Boolean {
            throw IllegalStateException("The trigger handles this event manually!")
        }

        override fun create(context: BGameContext): BUnit {
            throw IllegalStateException("The trigger handles this event manually!")
        }

        companion object {

            fun create(playerId: Long, x: Int, y: Int) = Event(playerId, x, y)
        }
    }

    companion object {

        private const val NEXT_WALL_POSITION = -1

        fun connect(context: BGameContext, playerId: Long) {
            BUnitOnCreateTrigger.connect(context) {
                BSkirmishHumanWallOnCreateTrigger(context, playerId)
            }
        }
    }
}