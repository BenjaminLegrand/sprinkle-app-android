package fr.legrand.sprinkle.presentation.ui.wrapper

import android.content.Context
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.data.model.Plant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

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
            val remainingDays = (Date().time - nextSprinkleDate.time)
                .toDuration(DurationUnit.MILLISECONDS).inDays.toInt()
            return when {
                remainingDays < 0 -> {
                    context.getString(R.string.next_sprinkle_date_reached)
                }
                remainingDays == 0 -> {
                    context.getString(R.string.next_sprinkle_date_today)
                }
                else -> {
                    context.resources.getQuantityString(
                        R.plurals.next_sprinkle_date_format,
                        remainingDays,
                        remainingDays
                    )
                }
            }
        } ?: context.getString(R.string.next_sprinkle_date_unknown)

    fun getImageUrl() = plant.imageUrl
}