package com.orego.battlecrane.bc.engine.api.context.storage.heap.implementation

import com.orego.battlecrane.bc.engine.api.context.storage.heap.BHeap
import com.orego.battlecrane.bc.engine.api.model.unit.BUnit

class BUnitHeap : BHeap<BUnit>() {

    override fun onPutObject(any: Any) {
        if (any is BUnit) {
            this.objectMap[any.unitId] = any
        }
    }

    override fun onRemoveObject(any: Any) {
        if (any is BUnit) {
            this.objectMap.remove(any.unitId)
        }
    }
}