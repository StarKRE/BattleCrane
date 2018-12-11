package com.orego.battlecrane.ui.model.api.render

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

abstract class BViewRender<K : Any, V : Any> {

    private var isInstalled = false

    protected lateinit var context: Context

    protected lateinit var factory: ViewFactory<K, V>

    protected lateinit var constraintLayout: ConstraintLayout

    protected val constraintSet = ConstraintSet()

    protected val temporaryViewList: MutableList<V> = mutableListOf()

    abstract fun draw()

    fun install(constraintLayout: ConstraintLayout, factory: ViewFactory<K, V>, context: Context) {
        if (!this.isInstalled) {
            this.factory = factory
            this.constraintLayout = constraintLayout
            this.context = context
        }
        this.draw()
    }

    open class ViewFactory<K : Any, V : Any> {

        private val builderMap = mutableMapOf<String, ViewBuilder<K, V>>()

        fun addBuilder(builder: ViewBuilder<K, V>) {
            val type = builder.type
            val builderMap = this.builderMap
            if (!builderMap.containsKey(type)) {
                builderMap[type] = builder
            } else {
                throw IllegalStateException("The type is added")
            }
        }

        fun build(unit: K, dimension: Int, context: Context, type: String): V {
            val builder = this.builderMap[type]
            return builder?.build(unit, dimension, context) ?: this.buildByDefault(unit, dimension, context, type)
        }

        open fun buildByDefault(unit: K, dimension: Int, context: Context, type: String): V {
            throw IllegalStateException("Not supported type!")
        }
    }

    interface ViewBuilder<K : Any, V : Any> {

        val type: String

        fun build(value: K, dimension: Int, context: Context): V
    }
}