package com.orego.battlecrane.ui.util

import android.view.View
import android.view.ViewTreeObserver

fun View.onMeasured(callback: () -> Unit) {

    val observer = this.viewTreeObserver
    observer.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

        override fun onGlobalLayout() {
            callback()
            observer.removeOnGlobalLayoutListener(this)
        }
    })
}