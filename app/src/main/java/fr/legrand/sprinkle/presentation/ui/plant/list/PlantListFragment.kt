package fr.legrand.sprinkle.presentation.ui.plant.list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.data.model.Exposition
import fr.legrand.sprinkle.data.model.Plant
import fr.legrand.sprinkle.databinding.FragmentPlantListBinding
import fr.legrand.sprinkle.presentation.ui.plant.list.item.PlantListAdapter
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import fr.legrand.viewbinding.extensions.BindingFragment
import kotlinx.android.synthetic.main.fragment_plant_list.fragment_plant_list_recycler_view
import java.util.Date
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@ExperimentalTime
@AndroidEntryPoint
class PlantListFragment : BindingFragment<FragmentPlantListBinding>() {
    @Inject
    lateinit var navigatorListener: PlantListFragmentNavigatorListener

    @Inject
    lateinit var plantListAdapter: PlantListAdapter

    override fun getBinding(view: View) = FragmentPlantListBinding.bind(view)

    override fun getLayoutId(): Int = R.layout.fragment_plant_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            fragment_plant_list_recycler_view.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            fragment_plant_list_recycler_view.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
            fragment_plant_list_recycler_view.adapter = plantListAdapter
        }

        plantListAdapter.setItems(listOf(
            PlantViewDataWrapper(Plant(1, "Plant 1", "Ficcus", "", "", Exposition.LOW_SUNLIGHT, Date(), Date(), 1.toDuration(DurationUnit.DAYS), 1.toDuration(DurationUnit.DAYS))),
            PlantViewDataWrapper(Plant(2, "Plant 1", "Ficcus", "", "", Exposition.LOW_SUNLIGHT, null, null, 1.toDuration(DurationUnit.DAYS), 1.toDuration(DurationUnit.DAYS))),
            PlantViewDataWrapper(Plant(3, "Plant 1", "Ficcus", "", "", Exposition.LOW_SUNLIGHT, null, null, 1.toDuration(DurationUnit.DAYS), 1.toDuration(DurationUnit.DAYS)))
        ))
    }
}