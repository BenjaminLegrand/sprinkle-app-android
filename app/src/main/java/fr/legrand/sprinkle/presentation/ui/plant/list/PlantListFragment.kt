package fr.legrand.sprinkle.presentation.ui.plant.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.databinding.FragmentPlantListBinding
import fr.legrand.sprinkle.presentation.ui.extensions.observeSafe
import fr.legrand.sprinkle.presentation.ui.extensions.setVisible
import fr.legrand.sprinkle.presentation.ui.plant.list.item.PlantListAdapter
import fr.legrand.viewbinding.extensions.BindingFragment
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
@AndroidEntryPoint
class PlantListFragment : BindingFragment<FragmentPlantListBinding>() {
    @Inject
    lateinit var navigatorListener: PlantListFragmentNavigatorListener

    @Inject
    lateinit var plantListAdapter: PlantListAdapter

    private val viewModel: PlantListFragmentViewModel by viewModels()

    override fun getBinding(view: View) = FragmentPlantListBinding.bind(view)

    override fun getLayoutId(): Int = R.layout.fragment_plant_list

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.retrievePlantList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding {
            (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentPlantListToolbar)
        }

        setupRecyclerView()
        setupBottomSheet()
        observePlantList()
    }

    private fun observePlantList() {
        viewModel.getPlantListLiveData().observeSafe(viewLifecycleOwner) {
            plantListAdapter.setItems(it)
            binding {
                fragmentPlantListRecyclerView.setVisible(it.isNotEmpty())
                fragmentPlantListPlaceholderGroup.setVisible(it.isEmpty())
            }
        }
    }

    private fun setupRecyclerView() {
        binding {
            fragmentPlantListRecyclerView.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            fragmentPlantListRecyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            )
            fragmentPlantListRecyclerView.adapter = plantListAdapter

            fragmentPlantListRecyclerView.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (dy > 0 && fragmentPlantListCreateFab.isShown) {
                            // Scroll in bottom direction
                            fragmentPlantListCreateFab.hide()
                        } else if (!fragmentPlantListCreateFab.isShown) {
                            // Scroll in top direction
                            fragmentPlantListCreateFab.show()
                        }
                    }
                }
            )
        }
    }

    private fun setupBottomSheet() {
        binding {
            BottomSheetBehavior.from(fragmentPlantListBottomsheet).state = BottomSheetBehavior.STATE_HIDDEN

            fragmentPlantListActions.setOnClickListener {
                changeBottomSheetState()
            }

            fragmentPlantListSprinkleAllActionArea.setOnClickListener {
                // TODO sprinkle all
                changeBottomSheetState()
            }

            fragmentPlantListDeletePlantsArea.setOnClickListener {
                // TODO delete UI
                changeBottomSheetState()
            }

            fragmentPlantListFertilizeArea.setOnClickListener {
                // TODO fertilize all
                changeBottomSheetState()
            }
        }
    }

    private fun changeBottomSheetState() {
        binding {
            val behavior = BottomSheetBehavior.from(fragmentPlantListBottomsheet)
            behavior.state = if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                BottomSheetBehavior.STATE_HIDDEN
            } else {
                BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }
}