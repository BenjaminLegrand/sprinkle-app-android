package fr.legrand.sprinkle.presentation.ui.extensions

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.setVisible(visible: Boolean) {
    if (visible) {
        show()
    } else {
        hide()
    }
}

private const val DOUBLE_CLICK_DELAY = 300
fun View.setOnClickDelayListener(listener: () -> Unit) {
    var lastClick = 0L
    setOnClickListener {
        if (System.currentTimeMillis() - lastClick > DOUBLE_CLICK_DELAY) {
            lastClick = System.currentTimeMillis()
            listener()
        }
    }
}
