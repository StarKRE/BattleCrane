package com.orego.battlecrane.bc.api.model.action

import com.orego.battlecrane.bc.api.context.BGameContext
import com.orego.battlecrane.bc.api.context.pipeline.BPipeline
import com.orego.battlecrane.bc.api.context.playerManager.player.BPlayer

abstract class BAction protected constructor(
    protected val context: BGameContext,
    protected var owner: BPlayer? = null
) {

    protected abstract fun performAction(): Boolean

    fun perform(): Boolean {
        val isSuccessful = this.performAction()
        if (isSuccessful) {
            this.actionObservers.values.forEach { it.onActionPerformed(this) }
        }
        return isSuccessful
    }

    val actionObservers = mutableMapOf<Long, Listener>()

    interface Listener {

        fun onActionPerformed(action: BAction)
    }

    abstract class Factory(private val pipeline: BPipeline) {

        fun create() : BAction {
            val action = this.createAction()
            this.pipeline.push(action)
            return action
        }

        protected abstract fun createAction(): BAction
    }
}