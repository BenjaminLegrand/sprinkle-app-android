package fr.legrand.sprinkle.presentation.ui.plant.create.icons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.legrand.sprinkle.R
import javax.inject.Inject

class PlantCreateIconListAdapter @Inject constructor() : RecyclerView.Adapter<PlantCreateIconViewHolder>() {

    var onIconSelectedListener: (Int?) -> Unit = {}

    private val items = mutableListOf<Int>()

    private var selectedIndex: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantCreateIconViewHolder {
        return PlantCreateIconViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_plant_create_icon_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlantCreateIconViewHolder, position: Int) {
        holder.bind(items[position], selectedIndex == position) { selectedPosition, selected ->
            val lastSelectedIndex = selectedIndex
            selectedIndex = if (!selected) {
                null
            } else {
                selectedPosition
            }
            lastSelectedIndex?.let { notifyItemChanged(it) }
            selectedIndex?.let {
                notifyItemChanged(it)
            }
            onIconSelectedListener(selectedIndex?.let { items[it] })
        }
    }

    fun setItems(newItems: List<Int>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

}