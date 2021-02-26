package fr.legrand.sprinkle.presentation.ui.plant.create.icons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.legrand.sprinkle.R
import javax.inject.Inject

class PlantCreateIconListAdapter @Inject constructor() : RecyclerView.Adapter<PlantCreateIconViewHolder>() {

    private val items = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantCreateIconViewHolder {
        return PlantCreateIconViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_plant_create_icon_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlantCreateIconViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(newItems : List<Int>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

}