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
class PlantListAdapter @Inject constructor() : ListAdapter<PlantViewDataWrapper, PlantListViewHolder>(diffUtil) {

    var onPlantClickListener: (Int) -> Unit = {}
    var onItemsDeleted: (List<Int>) -> Unit = {}

    private var deletionEnabled = false
    private val currentItems = mutableListOf<PlantViewDataWrapper>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantListViewHolder {
        return PlantListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_plant_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlantListViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val deleteState = payloads.firstOrNull { it is PlantItemDeleteState }
            (deleteState as? PlantItemDeleteState)?.let {
                holder.setDeleteState(it.state)
            } ?: super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: PlantListViewHolder, position: Int) {
        val wrapper = currentItems[position]

        val updatedListener: (Int) -> Unit = {
            if (deletionEnabled) {
                // Use indexOf as list can be updated
                onWrapperDeletedStateChanged(currentItems.indexOf(wrapper), wrapper, !wrapper.deleting)
            } else {
                onPlantClickListener(it)
            }
        }

        holder.bind(wrapper, updatedListener)
    }

    override fun getItemCount(): Int = currentItems.size

    fun setItems(newItems: List<PlantViewDataWrapper>) {
        currentItems.clear()
        currentItems.addAll(newItems)
        submitList(currentItems.toMutableList())
    }

    fun deleteSelectedItems() {
        val deletedItems = currentItems.filter { it.deleting }.map { it.getId() }
        currentItems.removeAll { it.deleting }
        submitList(currentItems.toMutableList())
        onItemsDeleted(deletedItems)
    }

    fun cancelDeletion() {
        currentItems.forEachIndexed { index, wrapper ->
            onWrapperDeletedStateChanged(index, wrapper, false)
        }
    }

    fun setDeletionEnabled(enabled: Boolean) {
        deletionEnabled = enabled
    }

    private fun onWrapperDeletedStateChanged(index: Int, wrapper: PlantViewDataWrapper, deleted: Boolean) {
        wrapper.deleting = deleted
        notifyItemChanged(index, PlantItemDeleteState(deleted))
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
