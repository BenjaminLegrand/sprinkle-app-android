package fr.legrand.sprinkle.presentation.ui.plant.list

import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.databinding.FragmentPlantListBinding
import fr.legrand.viewbinding.extensions.BindingFragment
import javax.inject.Inject

@AndroidEntryPoint
class PlantListFragment : BindingFragment<FragmentPlantListBinding>() {
    @Inject
    lateinit var navigatorListener: PlantListFragmentNavigatorListener

    override fun getBinding(view: View) = FragmentPlantListBinding.bind(view)

    override fun getLayoutId(): Int = R.layout.fragment_plant_list
}