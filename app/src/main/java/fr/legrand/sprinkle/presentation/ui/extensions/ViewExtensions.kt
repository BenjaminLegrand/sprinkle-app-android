package fr.legrand.sprinkle.presentation.ui.extensions

import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout

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

fun MotionLayout.onTransitionEnd(onTransitionEnd: () -> Unit) {
    setTransitionListener(
        object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                // Nothing to do
            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {
                // Nothing to do
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                setTransitionListener(null)
                onTransitionEnd()
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {
                // Nothing to do
            }

        }
    )
}
