package com.orego.battlecrane.bc.api.context.storage

import com.orego.battlecrane.bc.api.context.BGameContext
import com.orego.battlecrane.bc.api.context.pipeline.model.component.context.BContextComponent
import com.orego.battlecrane.bc.api.context.storage.heap.BHeap
import com.orego.battlecrane.bc.api.context.storage.heap.implementation.*
import com.orego.battlecrane.bc.api.scenario.BGameScenario

@BContextComponent
class BStorage(scenario: BGameScenario, context: BGameContext) {

    val heapMap = mutableMapOf<Class<*>, BHeap<*>>(
        BUnitHeap::class.java to BUnitHeap(),
        BActionHeap::class.java to BActionHeap(),
        BAttackableHeap::class.java to BAttackableHeap(),
        BLevelableHeap::class.java to BLevelableHeap(),
        BPlayerHeap::class.java to BPlayerHeap()
    )

    inline fun <reified T> getHeap(heapClazz: Class<T>): T = this.heapMap[heapClazz] as T

    fun addHeap(heap: BHeap<*>) {
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