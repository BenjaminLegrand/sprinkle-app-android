package fr.legrand.sprinkle.presentation.ui.plant.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.databinding.FragmentPlantListBinding
import fr.legrand.sprinkle.presentation.ui.extensions.hide
import fr.legrand.sprinkle.presentation.ui.extensions.show
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

    override fun getBinding(view: View) = FragmentPlantListBinding.bind(view)

    override fun getLayoutId(): Int = R.layout.fragment_plant_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        binding {
            (requireActivity() as AppCompatActivity).setSupportActionBar(fragmentPlantListToolbar)

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

            fragmentPlantListPlaceholderGroup.show()
            fragmentPlantListRecyclerView.hide()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.plant_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.plant_list_sprinkle_all_action -> {
                // TODO sprinkle all
                Toast.makeText(requireContext(), "Sprinkle all", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

