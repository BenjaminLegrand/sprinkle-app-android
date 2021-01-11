package fr.legrand.sprinkle.presentation.ui.extensions

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.AttrRes


fun Resources.Theme.getColor(@AttrRes attr: Int): Int {
    val typedValue = TypedValue()
    resolveAttribute(attr, typedValue, true)
    return typedValue.data
}