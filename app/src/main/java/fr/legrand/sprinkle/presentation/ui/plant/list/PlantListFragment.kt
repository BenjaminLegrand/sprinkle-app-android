package fr.legrand.sprinkle.presentation.ui.plant.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.databinding.FragmentPlantListBinding
import fr.legrand.sprinkle.presentation.ui.extensions.hide
import fr.legrand.sprinkle.presentation.ui.extensions.observeSafe
import fr.legrand.sprinkle.presentation.ui.extensions.setOnClickDelayListener
import fr.legrand.sprinkle.presentation.ui.extensions.setVisible
import fr.legrand.sprinkle.presentation.ui.extensions.show
import fr.legrand.sprinkle.presentation.ui.plant.list.item.PlantListAdapter
import fr.legrand.viewbinding.extensions.BindingFragment
import javax.inject.Inject
import kotlin.time.ExperimentalTime

private const val MAX_ALPHA = 1.0f
private const val MIN_ALPHA = 0.0f

@ExperimentalTime
@AndroidEntryPoint
class PlantListFragment : BindingFragment<FragmentPlantListBinding>() {
    @Inject
    lateinit var navigatorListener: PlantListFragmentNavigatorListener

    @Inject
    lateinit var plantListAdapter: PlantListAdapter

    private val viewModel: PlantListFragmentViewModel by viewModels()

    private var deletionEnabled = false

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
        setupDeletion()
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

            plantListAdapter.onPlantClickListener = { id ->
                // TODO click
                Toast.makeText(requireContext(), "Click $id", Toast.LENGTH_SHORT).show()
            }

            plantListAdapter.onItemsDeleted = { ids ->
                viewModel.deletePlants(ids)
                fragmentPlantListRecyclerView.setVisible(plantListAdapter.itemCount > 0)
                fragmentPlantListPlaceholderGroup.setVisible(plantListAdapter.itemCount <= 0)
            }

            fragmentPlantListRecyclerView.addOnScrollListener(
                object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (deletionEnabled) return

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
            val behavior = BottomSheetBehavior.from(fragmentPlantListBottomsheet)
            behavior.state = BottomSheetBehavior.STATE_HIDDEN
            fragmentPlantListBottomSheetBackground.alpha = MIN_ALPHA
            fragmentPlantListBottomSheetBackground.setOnClickDelayListener { changeBottomSheetState() }

            behavior.addBottomSheetCallback(
                object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        fragmentPlantListBottomSheetBackground.alpha = MAX_ALPHA + slideOffset
                    }

                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            fragmentPlantListBottomSheetBackground.hide()
                        } else if (newState == BottomSheetBehavior.STATE_SETTLING) {
                            fragmentPlantListBottomSheetBackground.show()
                        }
                    }
                }
            )

            fragmentPlantListActions.setOnClickDelayListener {
                changeBottomSheetState()
            }

            fragmentPlantListSprinkleAllActionArea.setOnClickDelayListener {
                // TODO sprinkle all
                if (deletionEnabled) {
                    hideDeletionUI()
                }
                changeBottomSheetState()
                Toast.makeText(requireContext(), "SPRINKLE ALL", Toast.LENGTH_SHORT).show()
            }

            fragmentPlantListDeletePlantsArea.setOnClickDelayListener {
                displayDeletionUI()
                changeBottomSheetState()
            }

            fragmentPlantListFertilizeArea.setOnClickDelayListener {
                // TODO fertilize all
                if (deletionEnabled) {
                    hideDeletionUI()
                }
                changeBottomSheetState()
                Toast.makeText(requireContext(), "FERTILIZE ALL", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupDeletion() {
        binding {
            fragmentPlantListDeleteCancelButton.setOnClickDelayListener {
                plantListAdapter.cancelDeletion()
                hideDeletionUI()
            }
            fragmentPlantListDeleteConfirmButton.setOnClickDelayListener {
                // TODO trigger deletion
                plantListAdapter.deleteSelectedItems()
                hideDeletionUI()
            }
        }
    }

    private fun hideDeletionUI() {
        deletionEnabled = false
        plantListAdapter.setDeletionEnabled(false)
        binding {
            fragmentPlantListCreateFab.show()
            fragmentPlantListDeleteButtonsGroup.hide()
        }
    }

    private fun displayDeletionUI() {
        deletionEnabled = true
        plantListAdapter.setDeletionEnabled(true)
        binding {
            fragmentPlantListCreateFab.hide()
            fragmentPlantListDeleteButtonsGroup.show()
        }
    }

    private fun changeBottomSheetState() {
        binding {
            val behavior = BottomSheetBehavior.from(fragmentPlantListBottomsheet)
            if (behavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                behavior.state = BottomSheetBehavior.STATE_HIDDEN
            } else if (behavior.state == BottomSheetBehavior.STATE_HIDDEN) {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }
}