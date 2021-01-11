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

    var onItemsDeleted: (List<Int>) -> Unit = {}
    var onPlantClickListener: (Int) -> Unit = {}

    private var deletionEnabled = false
    private val currentItems = mutableListOf<PlantViewDataWrapper>()
    private val deletedItems = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantListViewHolder {
        return PlantListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_plant_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlantListViewHolder, position: Int) {
        val wrapper = currentItems[position]
        val deleted = deletedItems.contains(wrapper.getId())

        // TODO check for deletion state
        val updatedListener: (Int) -> Unit = {
            if (deletionEnabled && deleted) {
                deletedItems.remove(it)
                notifyItemChanged(holder.adapterPosition)
            } else if (deletionEnabled) {
                deletedItems.add(it)
                notifyItemChanged(holder.adapterPosition)
            } else {
                onPlantClickListener
            }
        }

        holder.bind(wrapper, updatedListener, deleted)
    }

    fun setItems(newItems: List<PlantViewDataWrapper>) {
        currentItems.clear()
        currentItems.addAll(newItems)
        submitList(currentItems)
    }

    fun setDeletionEnabled(enabled: Boolean) {
        deletionEnabled = enabled
        if (!enabled) {
            deletedItems.clear()
        }
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