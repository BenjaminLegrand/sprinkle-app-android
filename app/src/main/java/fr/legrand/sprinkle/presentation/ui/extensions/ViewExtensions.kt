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
