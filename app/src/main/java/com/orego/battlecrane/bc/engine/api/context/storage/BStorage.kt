package com.orego.battlecrane.bc.engine.api.context.storage

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.context.storage.heap.BHeap
import com.orego.battlecrane.bc.engine.api.scenario.BGameScenario

class BStorage(private val context: BGameContext) {

    val heapMap = mutableMapOf<Class<*>, BHeap<*>>()

    fun setScenario(scenario: BGameScenario) {
        //Player:
        scenario.getPlayers(this.context).forEach { player ->
            this.addObject(player)
        }
        //Adjutant:
        scenario.getAdjutants(this.context).forEach { adjutant ->
            this.addObject(adjutant)
        }
        //Unit:
        scenario.getUnits(this.context).forEach { unit ->
            this.addObject(unit)
        }
    }

    inline fun <reified T> getHeap(heapClazz: Class<T>): T = this.heapMap[heapClazz] as T

    fun addHeap(heap : BHeap<*>) {
        this.heapMap[heap::class.java] = heap
    }

    fun addObject(any: Any) {
        val heaps = this.heapMap.values.toList()
        for (i in 0 until heaps.size) {
            heaps[i].addObject(any)
        }
    }

    fun removeObject(id: Long, heapClazz: Class<*>) {
        val heap = this.heapMap[heapClazz]!!
        val any: Any = heap[id]!!
        this.removeObject(any)
    }

    private fun removeObject(any: Any) {
        val heaps = this.heapMap.values.toList()
        for (i in 0 until heaps.size) {
            heaps[i].addObject(any)
        }
    }
}