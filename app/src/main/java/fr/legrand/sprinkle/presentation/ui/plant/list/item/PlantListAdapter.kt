package fr.legrand.sprinkle.presentation.ui.plant.list.item

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import dagger.hilt.android.qualifiers.ApplicationContext
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlantListAdapter @Inject constructor(
    @ApplicationContext private val context: Context
) : ListAdapter<PlantViewDataWrapper, PlantListViewHolder>(diffUtil) {

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
                holder.setDeleteState(it)
            } ?: super.onBindViewHolder(holder, position, payloads)
        }
    }

    override fun onBindViewHolder(holder: PlantListViewHolder, position: Int) {
        val wrapper = currentItems[position]

        val updatedListener: (Int) -> Unit = {
            if (deletionEnabled) {
                val newState = if (wrapper.deleting) {
                    PlantItemDeleteState.IDLE
                } else {
                    PlantItemDeleteState.DELETING
                }
                // Use indexOf as list can be updated between binds
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
        submitList(currentItems.toList())
    }

    fun deleteSelectedItems() {
        currentItems.onEachIndexed { index, wrapper ->
            if (wrapper.deleting) {
                notifyItemChanged(index, PlantItemDeleteState.DELETED)
            }
        }

        // Run code on main looper when all animations have ended
        Handler(Looper.getMainLooper()).postDelayed(
            {
                val deletedItems = currentItems.filter { it.deleting }.map { it.getId() }
                currentItems.removeAll { it.deleting }
                submitList(currentItems.toList())
                onItemsDeleted(deletedItems)
            },
            context.resources.getInteger(R.integer.plant_delete_anim_time).toLong()
        )
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
