package fr.legrand.sprinkle.presentation.ui.wrapper

import android.content.Context
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.data.model.Plant
import fr.legrand.sprinkle.data.model.TemplateIcon
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

private const val SPRINKLE_DATE_FORMAT = "dd/MM/yy"

@ExperimentalTime
data class PlantViewDataWrapper(private val plant: Plant) {

    var deleting = false

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

    fun getTemplateIconResource(): Int = when (plant.templateIcon) {
        TemplateIcon.TEMPLATE_1 -> R.drawable.ic_plant_template_01
        TemplateIcon.TEMPLATE_2 -> R.drawable.ic_plant_template_02
        TemplateIcon.TEMPLATE_3 -> R.drawable.ic_plant_template_03
        TemplateIcon.TEMPLATE_4 -> R.drawable.ic_plant_template_04
        TemplateIcon.TEMPLATE_5 -> R.drawable.ic_plant_template_05
        TemplateIcon.TEMPLATE_6 -> R.drawable.ic_plant_template_06
        TemplateIcon.TEMPLATE_7 -> R.drawable.ic_plant_template_07
        TemplateIcon.TEMPLATE_8 -> R.drawable.ic_plant_template_08
        TemplateIcon.TEMPLATE_9 -> R.drawable.ic_plant_template_09
        TemplateIcon.TEMPLATE_10 -> R.drawable.ic_plant_template_10
        TemplateIcon.TEMPLATE_11 -> R.drawable.ic_plant_template_11
        TemplateIcon.TEMPLATE_12 -> R.drawable.ic_plant_template_12
        TemplateIcon.TEMPLATE_13 -> R.drawable.ic_plant_template_13
        TemplateIcon.TEMPLATE_14 -> R.drawable.ic_plant_template_14
        TemplateIcon.TEMPLATE_15 -> R.drawable.ic_plant_template_15
        TemplateIcon.TEMPLATE_16 -> R.drawable.ic_plant_template_16
        TemplateIcon.TEMPLATE_17 -> R.drawable.ic_plant_template_17
        TemplateIcon.TEMPLATE_18 -> R.drawable.ic_plant_template_18
        TemplateIcon.TEMPLATE_19 -> R.drawable.ic_plant_template_19
        TemplateIcon.TEMPLATE_20 -> R.drawable.ic_plant_template_20
        TemplateIcon.TEMPLATE_21 -> R.drawable.ic_plant_template_21
    }
}