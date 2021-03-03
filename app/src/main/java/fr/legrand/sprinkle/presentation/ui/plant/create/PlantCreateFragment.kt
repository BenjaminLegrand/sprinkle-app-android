package fr.legrand.sprinkle.presentation.ui.plant.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.databinding.FragmentPlantCreateBinding
import fr.legrand.sprinkle.presentation.ui.plant.create.icons.PlantCreateIconListAdapter
import fr.legrand.sprinkle.presentation.ui.plant.create.navigator.PlantCreateFragmentNavigatorListener
import fr.legrand.viewbinding.extensions.BindingFragment
import javax.inject.Inject
import kotlin.math.max
import kotlin.time.ExperimentalTime

private const val ICON_COLUMNS_COUNT = 3
private const val DEFAULT_SPRINKLING_VALUE = 1
private const val DEFAULT_FERTILIZE_VALUE = 1
private const val DAYS_IN_WEEK = 7
private const val WEEKS_IN_MONTH = 4

@ExperimentalTime
@AndroidEntryPoint
class PlantCreateFragment : BindingFragment<FragmentPlantCreateBinding>() {

    @Inject
    lateinit var plantCreateIconListAdapter: PlantCreateIconListAdapter

    @Inject
    lateinit var navigatorListener: PlantCreateFragmentNavigatorListener

    private val viewModel: PlantCreateFragmentViewModel by viewModels()

    private var currentSprinklingValue = DEFAULT_SPRINKLING_VALUE
    private var currentFertilizeValue = DEFAULT_FERTILIZE_VALUE

    override fun getBinding(view: View) = FragmentPlantCreateBinding.bind(view)

    override fun getLayoutId(): Int = R.layout.fragment_plant_create

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupIconList()
        setupExposition()
        setupSprinkling()
        setupFertilize()
        setupButtons()
    }

    private fun setupButtons() {
        binding {
            fragmentPlantCreateCancelButton.setOnClickListener {
                navigatorListener.onCancelClick(requireActivity())
            }
        }
    }

    private fun setupFertilize() {
        binding {
            fragmentPlantCreateFertilizeValue.text =
                getFertilizeIntervalText(currentFertilizeValue)

            fragmentPlantCreateFertilizeLess.setOnClickListener {
                currentFertilizeValue = max(currentFertilizeValue - 1, DEFAULT_FERTILIZE_VALUE)
                fragmentPlantCreateFertilizeValue.text =
                    getFertilizeIntervalText(currentFertilizeValue)

            }
            fragmentPlantCreateFertilizeMore.setOnClickListener {
                currentFertilizeValue++
                fragmentPlantCreateFertilizeValue.text =
                    getFertilizeIntervalText(currentFertilizeValue)
            }
        }
    }

    private fun setupSprinkling() {
        binding {
            fragmentPlantCreateSprinklingValue.text =
                getSprinklingIntervalText(currentSprinklingValue)

            fragmentPlantCreateSprinklingLess.setOnClickListener {
                currentSprinklingValue = max(currentSprinklingValue - 1, DEFAULT_SPRINKLING_VALUE)
                fragmentPlantCreateSprinklingValue.text =
                    getSprinklingIntervalText(currentSprinklingValue)

            }
            fragmentPlantCreateSprinklingMore.setOnClickListener {
                currentSprinklingValue++
                fragmentPlantCreateSprinklingValue.text =
                    getSprinklingIntervalText(currentSprinklingValue)
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
                fragmentPlantCreateExpositionLowSunlight.isSelected = false
                fragmentPlantCreateExpositionSunlight.isSelected = false
            }
            fragmentPlantCreateExpositionLowSunlight.setOnClickListener {
                fragmentPlantCreateExpositionLowSunlight.isSelected =
                    !fragmentPlantCreateExpositionLowSunlight.isSelected
                fragmentPlantCreateExpositionShadow.isSelected = false
                fragmentPlantCreateExpositionSunlight.isSelected = false
            }
            fragmentPlantCreateExpositionSunlight.setOnClickListener {
                fragmentPlantCreateExpositionSunlight.isSelected = !fragmentPlantCreateExpositionSunlight.isSelected
                fragmentPlantCreateExpositionShadow.isSelected = false
                fragmentPlantCreateExpositionLowSunlight.isSelected = false
            }
        }
    }

    private fun setupIconList() {
        binding {
            fragmentPlantCreateIconList.adapter = plantCreateIconListAdapter
            fragmentPlantCreateIconList.layoutManager =
                StaggeredGridLayoutManager(ICON_COLUMNS_COUNT, StaggeredGridLayoutManager.VERTICAL)

            plantCreateIconListAdapter.setItems(
                listOf(
                    R.drawable.ic_plant_template_01,
                    R.drawable.ic_plant_template_02,
                    R.drawable.ic_plant_template_03,
                    R.drawable.ic_plant_template_04,
                    R.drawable.ic_plant_template_05,
                    R.drawable.ic_plant_template_06,
                    R.drawable.ic_plant_template_07,
                    R.drawable.ic_plant_template_08,
                    R.drawable.ic_plant_template_09,
                    R.drawable.ic_plant_template_10,
                    R.drawable.ic_plant_template_11,
                    R.drawable.ic_plant_template_12,
                    R.drawable.ic_plant_template_13,
                    R.drawable.ic_plant_template_14,
                    R.drawable.ic_plant_template_15,
                    R.drawable.ic_plant_template_16,
                    R.drawable.ic_plant_template_17,
                    R.drawable.ic_plant_template_18,
                    R.drawable.ic_plant_template_19,
                    R.drawable.ic_plant_template_20,
                    R.drawable.ic_plant_template_21,
                )
            )
        }
    }
}