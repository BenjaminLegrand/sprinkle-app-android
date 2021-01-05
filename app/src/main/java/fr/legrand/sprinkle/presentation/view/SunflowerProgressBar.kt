package fr.legrand.sprinkle.presentation.view

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import fr.legrand.sprinkle.R

private const val SUNFLOWER_ANIM_DURATION = 2000L
private const val START_ROTATION = 0f
private const val END_ROTATION = 360f
private const val ROTATION_PROPERTY_NAME = "rotation"

class SunflowerProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setImageResource(R.drawable.ic_sunflower)

        ObjectAnimator.ofFloat(
            this,
            ROTATION_PROPERTY_NAME,
            START_ROTATION,
            END_ROTATION
        ).apply {
            duration = SUNFLOWER_ANIM_DURATION
            repeatMode = ObjectAnimator.RESTART
            repeatCount = ObjectAnimator.INFINITE
        }.start()
    }
}
