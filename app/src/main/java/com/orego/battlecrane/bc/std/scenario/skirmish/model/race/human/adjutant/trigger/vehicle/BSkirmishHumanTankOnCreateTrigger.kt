package com.orego.battlecrane.bc.std.scenario.skirmish.model.race.human.adjutant.trigger.vehicle

import com.orego.battlecrane.bc.api.context.BGameContext
import com.orego.battlecrane.bc.api.context.pipeline.model.component.adjutant.BAdjutantComponent
import com.orego.battlecrane.bc.api.context.pipeline.model.event.BEvent
import com.orego.battlecrane.bc.api.model.unit.trigger.BOnCreateUnitTrigger
import com.orego.battlecrane.bc.api.model.unit.BUnit

@BAdjutantComponent
class BSkirmishHumanTankOnCreateTrigger private constructor(context: BGameContext, playerId: Long) :
    BOnCreateUnitTrigger(context, playerId) {

    companion object {

        fun connect(context: BGameContext, playerId: Long) {
            BOnCreateUnitTrigger.connect(context) {
                BSkirmishHumanTankOnCreateTrigger(
                    context,
                    playerId
                )
            }
        }
    }

    override fun handle(event: BEvent): BEvent? {
        if (event is Event) {
            return super.handle(event)
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

    class Event private constructor(playerId: Long, x: Int, y: Int) : BOnCreateUnitTrigger.Event(playerId, x, y) {

        override fun create(context: BGameContext): BUnit {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        companion object {

            fun create(playerId: Long, x: Int, y: Int) =
                Event(
                    playerId,
                    x,
                    y
                )
        }
    }
}