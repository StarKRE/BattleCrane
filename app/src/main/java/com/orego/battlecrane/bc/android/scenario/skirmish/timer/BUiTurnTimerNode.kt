package com.orego.battlecrane.bc.android.scenario.skirmish.timer

import com.orego.battlecrane.bc.engine.api.context.pipeline.model.event.BEvent
import com.orego.battlecrane.bc.engine.api.context.pipeline.model.node.BNode
import com.orego.battlecrane.bc.engine.api.model.player.BPlayer
import com.orego.battlecrane.bc.engine.api.util.trigger.turn.BTurnTimerTrigger
import com.orego.battlecrane.bc.android.api.context.BUiGameContext

class BUiTurnTimerNode(private val uiContext: BUiGameContext, private val playerId: Long) :
    BNode(uiContext.gameContext) {

    private val turnTimeProgressBar = this.uiContext.uiProvider.lineProgressBar

    companion object {

        private const val DEFAULT_MIN = 0

        fun connect(uiContext: BUiGameContext, player: BPlayer) {
            val playerId = player.playerId
            val turnTimerNode = uiContext.gameContext.pipeline.findNodeBy { node ->
                node is BTurnTimerTrigger && node.playerId == playerId
            }
            turnTimerNode.connectInnerPipe(
                BUiTurnTimerNode(
                    uiContext,
                    playerId
                ).intoPipe())
        }
    }

    override fun handle(event: BEvent): BEvent? {
        val animation: suspend () -> Unit = when (event) {
            is BTurnTimerTrigger.StartEvent -> {
                {
                    this.turnTimeProgressBar.min =
                            DEFAULT_MIN
                    this.turnTimeProgressBar.max = (event.turnTime / BTurnTimerTrigger.SECOND).toInt()
                }
            }
            is BTurnTimerTrigger.StopEvent -> {
                {
                    this.turnTimeProgressBar.min =
                            DEFAULT_MIN
                    this.turnTimeProgressBar.max =
                            DEFAULT_MIN
                }
            }
            is BTurnTimerTrigger.TickEvent -> {
                {
                    this.turnTimeProgressBar.progress = (event.timeLeft / BTurnTimerTrigger.SECOND).toInt()
                }
            }
            else -> {
                {}
            }
        }
        this.uiContext.animationPipe.addAnimation(animation)
        return null
    }
}