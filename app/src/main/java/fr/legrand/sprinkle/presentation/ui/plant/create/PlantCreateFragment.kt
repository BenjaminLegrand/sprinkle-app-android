package fr.legrand.sprinkle.presentation.ui.plant.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.databinding.FragmentPlantCreateBinding
import fr.legrand.sprinkle.presentation.ui.plant.create.icons.PlantCreateIconListAdapter
import fr.legrand.viewbinding.extensions.BindingFragment
import javax.inject.Inject
import kotlin.time.ExperimentalTime

private const val ICON_COLUMNS_COUNT = 3

@ExperimentalTime
@AndroidEntryPoint
class PlantCreateFragment : BindingFragment<FragmentPlantCreateBinding>() {

    private val viewModel: PlantCreateFragmentViewModel by viewModels()

    @Inject
    lateinit var plantCreateIconListAdapter: PlantCreateIconListAdapter

    override fun getBinding(view: View) = FragmentPlantCreateBinding.bind(view)

    override fun getLayoutId(): Int = R.layout.fragment_plant_create

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupIconList()
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