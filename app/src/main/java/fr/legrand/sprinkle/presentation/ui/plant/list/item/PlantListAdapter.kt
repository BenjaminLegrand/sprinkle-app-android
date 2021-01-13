package fr.legrand.sprinkle.presentation.ui.plant.list.item

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlantListAdapter @Inject constructor() : RecyclerView.Adapter<PlantListViewHolder>() {

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
                holder.setDeleteState(it) {
                    if (it == PlantItemDeleteState.DELETED) {
                        notifyItemRemoved(position)
                    }
                }
            } ?: super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: PlantListViewHolder, position: Int) {
        val wrapper = currentItems[position]

        val updatedListener: (Int) -> Unit = {
            if (deletionEnabled) {
                // Use indexOf as list can be updated
                val newState = if (wrapper.deleting) {
                    PlantItemDeleteState.IDLE
                } else {
                    PlantItemDeleteState.DELETING
                }
                onWrapperDeletedStateChanged(currentItems.indexOf(wrapper), wrapper, newState)
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
        notifyDataSetChanged()
    }

    fun deleteSelectedItems() {
        currentItems.onEachIndexed { index, wrapper ->
            if (wrapper.deleting) {
                notifyItemChanged(index, PlantItemDeleteState.DELETED)
            }
        }

        val deletedItems = currentItems.filter { it.deleting }.map { it.getId() }
        currentItems.removeAll { it.deleting }
        onItemsDeleted(deletedItems)
    }

    fun cancelDeletion() {
        currentItems.forEachIndexed { index, wrapper ->
            onWrapperDeletedStateChanged(index, wrapper, PlantItemDeleteState.IDLE)
        }
    }

    fun setDeletionEnabled(enabled: Boolean) {
        deletionEnabled = enabled
    }

    private fun onWrapperDeletedStateChanged(index: Int, wrapper: PlantViewDataWrapper, state: PlantItemDeleteState) {
        wrapper.deleting = when (state) {
            PlantItemDeleteState.IDLE -> false
            PlantItemDeleteState.DELETING, PlantItemDeleteState.DELETED -> true
        }
        notifyItemChanged(index, state)
    }
}