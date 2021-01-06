package fr.legrand.sprinkle.presentation.ui.wrapper

import android.content.Context
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.data.model.Plant
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.time.ExperimentalTime

private const val SPRINKLE_DATE_FORMAT = "dd/MM/yy"

@ExperimentalTime
data class PlantViewDataWrapper(private val plant: Plant) {

    fun getId() = plant.id

    fun getName() = plant.name

    fun getSpecies() = plant.species

    fun getFormattedLastSprinkleDate(context: Context): String =
        plant.lastSprinkleDate?.let { lastSprinkleDate ->
            val dateFormat = SimpleDateFormat(SPRINKLE_DATE_FORMAT, Locale.getDefault())
            return context.getString(
                R.string.last_sprinkle_date_format,
                dateFormat.format(lastSprinkleDate)
            )
        } ?: context.getString(R.string.last_sprinkle_date_unknown)

    fun getFormattedNextSprinkleDate(context: Context): String =
        plant.nextSprinkleDate?.let { nextSprinkleDate ->
            val dateFormat = SimpleDateFormat(SPRINKLE_DATE_FORMAT, Locale.getDefault())
            return context.getString(
                R.string.next_sprinkle_date_format,
                dateFormat.format(nextSprinkleDate)
            )
        } ?: context.getString(R.string.next_sprinkle_date_unknown)
}