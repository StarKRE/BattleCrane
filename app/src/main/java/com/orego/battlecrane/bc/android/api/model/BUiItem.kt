package com.orego.battlecrane.bc.android.api.model

import android.view.View
import com.orego.battlecrane.bc.android.api.context.BUiGameContext

abstract class BUiItem {

    abstract fun createView(uiGameContext: BUiGameContext) : View

    abstract fun updateView(uiGameContext: BUiGameContext)

    abstract fun destroyView(uiGameContext: BUiGameContext)

    /**
     * Creates a uiUnit.
     */

    abstract class Builder {

        protected open val configurations = mutableListOf<(BUiGameContext, BUiItem) -> Unit>()

        protected abstract fun onCreate(uiGameContext: BUiGameContext): BUiItem

        open fun build(uiGameContext: BUiGameContext): BUiItem {
            val uiItem = this.onCreate(uiGameContext)
            this.configurations.forEach { configure -> configure(uiGameContext, uiItem) }
            return uiItem
        }

        fun addConfiguration(configure : (BUiGameContext, BUiItem) -> Unit) {
            this.configurations += configure
        }

        fun removeConfiguration(configure : (BUiGameContext, BUiItem) -> Unit) {
            this.configurations -= configure
        }
    }
}