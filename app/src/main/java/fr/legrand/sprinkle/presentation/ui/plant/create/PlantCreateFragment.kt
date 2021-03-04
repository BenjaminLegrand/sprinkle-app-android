package fr.legrand.sprinkle.presentation.ui.plant.create

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.data.model.Exposition
import fr.legrand.sprinkle.data.model.TemplateIcon
import fr.legrand.sprinkle.databinding.FragmentPlantCreateBinding
import fr.legrand.sprinkle.presentation.ui.extensions.getContent
import fr.legrand.sprinkle.presentation.ui.extensions.setOnClickDelayListener
import fr.legrand.sprinkle.presentation.ui.plant.create.icons.PlantCreateIconListAdapter
import fr.legrand.sprinkle.presentation.ui.plant.create.navigator.PlantCreateFragmentNavigatorListener
import fr.legrand.viewbinding.extensions.BindingFragment
import javax.inject.Inject
import kotlin.math.max
import kotlin.time.ExperimentalTime

private const val ICON_COLUMNS_COUNT = 3
private const val DEFAULT_SPRINKLING_INTERVAL = 1
private const val DEFAULT_FERTILIZE_INTERVAL = 1
private const val DAYS_IN_WEEK = 7
private const val WEEKS_IN_MONTH = 4

private val ICONS_MAP = mapOf(
    R.drawable.ic_plant_template_01 to TemplateIcon.TEMPLATE_1,
    R.drawable.ic_plant_template_02 to TemplateIcon.TEMPLATE_2,
    R.drawable.ic_plant_template_03 to TemplateIcon.TEMPLATE_3,
    R.drawable.ic_plant_template_04 to TemplateIcon.TEMPLATE_4,
    R.drawable.ic_plant_template_05 to TemplateIcon.TEMPLATE_5,
    R.drawable.ic_plant_template_06 to TemplateIcon.TEMPLATE_6,
    R.drawable.ic_plant_template_07 to TemplateIcon.TEMPLATE_7,
    R.drawable.ic_plant_template_08 to TemplateIcon.TEMPLATE_8,
    R.drawable.ic_plant_template_09 to TemplateIcon.TEMPLATE_9,
    R.drawable.ic_plant_template_10 to TemplateIcon.TEMPLATE_10,
    R.drawable.ic_plant_template_11 to TemplateIcon.TEMPLATE_11,
    R.drawable.ic_plant_template_12 to TemplateIcon.TEMPLATE_12,
    R.drawable.ic_plant_template_13 to TemplateIcon.TEMPLATE_13,
    R.drawable.ic_plant_template_14 to TemplateIcon.TEMPLATE_14,
    R.drawable.ic_plant_template_15 to TemplateIcon.TEMPLATE_15,
    R.drawable.ic_plant_template_16 to TemplateIcon.TEMPLATE_16,
    R.drawable.ic_plant_template_17 to TemplateIcon.TEMPLATE_17,
    R.drawable.ic_plant_template_18 to TemplateIcon.TEMPLATE_18,
    R.drawable.ic_plant_template_19 to TemplateIcon.TEMPLATE_19,
    R.drawable.ic_plant_template_20 to TemplateIcon.TEMPLATE_20,
    R.drawable.ic_plant_template_21 to TemplateIcon.TEMPLATE_21,
)

@ExperimentalTime
@AndroidEntryPoint
class PlantCreateFragment : BindingFragment<FragmentPlantCreateBinding>() {

    @Inject
    lateinit var plantCreateIconListAdapter: PlantCreateIconListAdapter

    @Inject
    lateinit var navigatorListener: PlantCreateFragmentNavigatorListener

    private val viewModel: PlantCreateFragmentViewModel by viewModels()

    private var currentWinterSprinklingInterval = DEFAULT_SPRINKLING_INTERVAL
    private var currentWinterFertilizeInterval = DEFAULT_FERTILIZE_INTERVAL
    private var currentSummerSprinklingInterval = DEFAULT_SPRINKLING_INTERVAL
    private var currentSummerFertilizeInterval = DEFAULT_FERTILIZE_INTERVAL
    private var currentIcon: TemplateIcon? = null
    private var currentExposition: Exposition? = null

    override fun getBinding(view: View) = FragmentPlantCreateBinding.bind(view)

    override fun getLayoutId(): Int = R.layout.fragment_plant_create

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupIconList()
        setupExposition()
        setupWinterSprinkling()
        setupSummerSprinkling()
        setupWinterFertilize()
        setupSummerFertilize()
        setupButtons()

        binding {
            fragmentPlantCreateName.addTextChangedListener { checkFormValid() }
            fragmentPlantCreateSpecies.addTextChangedListener { checkFormValid() }
            fragmentPlantCreateLocation.addTextChangedListener { checkFormValid() }
        }
    }

    private fun setupButtons() {
        binding {
            fragmentPlantCreateCancelButton.setOnClickDelayListener {
                navigatorListener.onCancelClick(requireActivity())
            }
            fragmentPlantCreateSubmitButton.setOnClickDelayListener {
                viewModel.createPlant(
                    fragmentPlantCreateName.getContent(),
                    fragmentPlantCreateSpecies.getContent(),
                    fragmentPlantCreateLocation.getContent(),
                    currentWinterSprinklingInterval,
                    currentWinterFertilizeInterval,
                    currentSummerSprinklingInterval,
                    currentSummerFertilizeInterval,
                    currentExposition!!,
                    currentIcon!!
                )
            }
        }
    }

    private fun setupWinterFertilize() {
        binding {
            fragmentPlantCreateWinterFertilizeInterval.text =
                getFertilizeIntervalText(currentWinterFertilizeInterval)

            fragmentPlantCreateWinterFertilizeLess.setOnClickListener {
                currentWinterFertilizeInterval = max(currentWinterFertilizeInterval - 1, DEFAULT_FERTILIZE_INTERVAL)
                fragmentPlantCreateWinterFertilizeInterval.text =
                    getFertilizeIntervalText(currentWinterFertilizeInterval)
            }
            fragmentPlantCreateWinterFertilizeMore.setOnClickListener {
                currentWinterFertilizeInterval++
                fragmentPlantCreateWinterFertilizeInterval.text =
                    getFertilizeIntervalText(currentWinterFertilizeInterval)
            }
        }
    }

    private fun setupSummerFertilize() {
        binding {
            fragmentPlantCreateSummerFertilizeInterval.text =
                getFertilizeIntervalText(currentSummerFertilizeInterval)

            fragmentPlantCreateSummerFertilizeLess.setOnClickListener {
                currentSummerFertilizeInterval = max(currentSummerFertilizeInterval - 1, DEFAULT_FERTILIZE_INTERVAL)
                fragmentPlantCreateSummerFertilizeInterval.text =
                    getFertilizeIntervalText(currentSummerFertilizeInterval)
            }
            fragmentPlantCreateSummerFertilizeMore.setOnClickListener {
                currentSummerFertilizeInterval++
                fragmentPlantCreateSummerFertilizeInterval.text =
                    getFertilizeIntervalText(currentSummerFertilizeInterval)
            }
        }
    }

    private fun setupWinterSprinkling() {
        binding {
            fragmentPlantCreateWinterSprinklingInterval.text =
                getSprinklingIntervalText(currentWinterSprinklingInterval)

            fragmentPlantCreateWinterSprinklingLess.setOnClickListener {
                currentWinterSprinklingInterval = max(currentWinterSprinklingInterval - 1, DEFAULT_SPRINKLING_INTERVAL)
                fragmentPlantCreateWinterSprinklingInterval.text =
                    getSprinklingIntervalText(currentWinterSprinklingInterval)
            }
            fragmentPlantCreateWinterSprinklingMore.setOnClickListener {
                currentWinterSprinklingInterval++
                fragmentPlantCreateWinterSprinklingInterval.text =
                    getSprinklingIntervalText(currentWinterSprinklingInterval)
            }
        }
    }

    private fun setupSummerSprinkling() {
        binding {
            fragmentPlantCreateSummerSprinklingInterval.text =
                getSprinklingIntervalText(currentSummerSprinklingInterval)

            fragmentPlantCreateSummerSprinklingLess.setOnClickListener {
                currentSummerSprinklingInterval = max(currentSummerSprinklingInterval - 1, DEFAULT_SPRINKLING_INTERVAL)
                fragmentPlantCreateSummerSprinklingInterval.text =
                    getSprinklingIntervalText(currentSummerSprinklingInterval)
            }
            fragmentPlantCreateSummerSprinklingMore.setOnClickListener {
                currentSummerSprinklingInterval++
                fragmentPlantCreateSummerSprinklingInterval.text =
                    getSprinklingIntervalText(currentSummerSprinklingInterval)
            }
        }
    }

    private fun getSprinklingIntervalText(value: Int): String {
        val weekCount = value / DAYS_IN_WEEK
        val dayCount = value % DAYS_IN_WEEK
        return when {
            weekCount == 0 -> {
                resources.getQuantityString(R.plurals.plant_creation_sprinkling_format_days, dayCount, dayCount)
            }
            dayCount == 0 -> {
                resources.getQuantityString(R.plurals.plant_creation_sprinkling_format_weeks, weekCount, weekCount)
            }
            else -> {
                val daysString =
                    resources.getQuantityString(R.plurals.plant_creation_sprinkling_format_days, dayCount, dayCount)
                val weeksString =
                    resources.getQuantityString(R.plurals.plant_creation_sprinkling_format_weeks, weekCount, weekCount)
                getString(R.string.plant_creation_sprinkling_format_weeks_days, weeksString, daysString)
            }
        }
    }

    private fun getFertilizeIntervalText(value: Int): String {
        val monthCount = value / WEEKS_IN_MONTH
        val weekCount = value % WEEKS_IN_MONTH
        return when {
            monthCount == 0 -> {
                resources.getQuantityString(R.plurals.plant_creation_fertilize_format_weeks, weekCount, weekCount)
            }
            weekCount == 0 -> {
                resources.getQuantityString(R.plurals.plant_creation_fertilize_format_months, monthCount, monthCount)
            }
            else -> {
                val weeksString =
                    resources.getQuantityString(R.plurals.plant_creation_fertilize_format_weeks, weekCount, weekCount)
                val monthsString =
                    resources.getQuantityString(
                        R.plurals.plant_creation_fertilize_format_months,
                        monthCount,
                        monthCount
                    )
                getString(R.string.plant_creation_fertilize_format_months_weeks, monthsString, weeksString)
            }
        }
    }

    private fun setupExposition() {
        binding {
            fragmentPlantCreateExpositionShadow.setOnClickListener {
                fragmentPlantCreateExpositionShadow.isSelected = !fragmentPlantCreateExpositionShadow.isSelected
                currentExposition = if (fragmentPlantCreateExpositionShadow.isSelected) Exposition.SHADOW else null
                fragmentPlantCreateExpositionLowSunlight.isSelected = false
                fragmentPlantCreateExpositionSunlight.isSelected = false
                checkFormValid()
            }
            fragmentPlantCreateExpositionLowSunlight.setOnClickListener {
                fragmentPlantCreateExpositionLowSunlight.isSelected =
                    !fragmentPlantCreateExpositionLowSunlight.isSelected
                currentExposition =
                    if (fragmentPlantCreateExpositionLowSunlight.isSelected) Exposition.LOW_SUNLIGHT else null
                fragmentPlantCreateExpositionShadow.isSelected = false
                fragmentPlantCreateExpositionSunlight.isSelected = false
                checkFormValid()
            }
            fragmentPlantCreateExpositionSunlight.setOnClickListener {
                fragmentPlantCreateExpositionSunlight.isSelected = !fragmentPlantCreateExpositionSunlight.isSelected
                currentExposition = if (fragmentPlantCreateExpositionSunlight.isSelected) Exposition.SUNLIGHT else null
                fragmentPlantCreateExpositionShadow.isSelected = false
                fragmentPlantCreateExpositionLowSunlight.isSelected = false
                checkFormValid()
            }
        }
    }

    private fun setupIconList() {
        binding {
            fragmentPlantCreateIconList.adapter = plantCreateIconListAdapter
            fragmentPlantCreateIconList.layoutManager =
                StaggeredGridLayoutManager(ICON_COLUMNS_COUNT, StaggeredGridLayoutManager.VERTICAL)

            plantCreateIconListAdapter.onIconSelectedListener = { value ->
                currentIcon = value?.let { ICONS_MAP[it] }
                checkFormValid()
            }
            plantCreateIconListAdapter.setItems(ICONS_MAP.keys.toList())
        }
    }

    private fun checkFormValid() {
        binding {
            fragmentPlantCreateSubmitButton.isEnabled = currentIcon != null &&
                currentExposition != null &&
                fragmentPlantCreateName.getContent().isNotBlank() &&
                fragmentPlantCreateSpecies.getContent().isNotBlank() &&
                fragmentPlantCreateLocation.getContent().isNotBlank()
        }
    }
}