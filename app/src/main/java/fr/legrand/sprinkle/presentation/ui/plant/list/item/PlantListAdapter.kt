package fr.legrand.sprinkle.presentation.ui.plant.list.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlantListAdapter @Inject constructor() :
    ListAdapter<PlantViewDataWrapper, PlantListViewHolder>(diffUtil) {

    private val currentItems = mutableListOf<PlantViewDataWrapper>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantListViewHolder {
        return PlantListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_plant_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlantListViewHolder, position: Int) {
        holder.bind(currentItems[position])
    }

    fun setItems(newItems: List<PlantViewDataWrapper>) {
        currentItems.clear()
        currentItems.addAll(newItems)
        submitList(currentItems)
    }
}

@ExperimentalTime
private val diffUtil = object : DiffUtil.ItemCallback<PlantViewDataWrapper>() {
    override fun areItemsTheSame(
        oldItem: PlantViewDataWrapper,
        newItem: PlantViewDataWrapper
    ): Boolean = oldItem.getId() == newItem.getId()

    override fun areContentsTheSame(
        oldItem: PlantViewDataWrapper,
        newItem: PlantViewDataWrapper
    ): Boolean = oldItem == newItem
}