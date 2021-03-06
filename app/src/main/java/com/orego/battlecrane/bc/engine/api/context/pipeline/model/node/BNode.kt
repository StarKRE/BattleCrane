package com.orego.battlecrane.bc.engine.api.context.pipeline.model.node

import com.orego.battlecrane.bc.engine.api.context.BGameContext
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.event.BEvent
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.pipe.BPipe

/**
 * Handles game events.
 */

abstract class BNode(val context: BGameContext) {

    /**
     * Node.
     */

    val id = this.context.contextGenerator.getIdGenerator(BNode::class.java).generateId()

    open val name: String? = null

    protected open val pipeMap = mutableMapOf<Long, BPipe>()

    abstract fun handle(event: BEvent): BEvent?

    open fun connectInnerPipe(pipe: BPipe) {
        this.pipeMap[pipe.id] = pipe
    }

    open fun disconnectInnerPipe(pipeId: Long) {
        this.pipeMap.remove(pipeId)
    }

    fun findNodeBy(condition: (BNode) -> Boolean): BNode? {
        if (condition(this)) {
            return this
        } else {
            val pipes = this.pipeMap.values.toList()
            for (i in 0 until pipes.size) {
                val result = pipes[i].findNodeBy(condition)
                if (result != null) {
                    return result
                }
            }
            return null
        }
    }

    fun findPipeBy(condition: (BPipe) -> Boolean): BPipe? {
        val pipes = this.pipeMap.values.toList()
        for (i in 0 until pipes.size) {
            val result = pipes[i].findPipeBy(condition)
            if (result != null) {
                return result
            }
        }
        return null
    }

    open fun intoPipe() = BPipe(this.context, mutableListOf(this))

    protected fun pushToInnerPipes(event: BEvent): BEvent {
        val pipes = this.pipeMap.values.toList()
        val size = pipes.size
        for (i in 0 until size) {
            pipes[i].push(event)
        }
        return event
    }

    fun removeUnusedComponents() {
        val pipes = this.pipeMap.values.toList()
        for (i in 0 until pipes.size) {
            val pipe = pipes[i]
            pipe.removeUnusedComponents()
            if (pipe.isFinished()) {
                this.pipeMap.remove(pipe.id)
            }
        }
    }

    open fun isFinished() = false
}