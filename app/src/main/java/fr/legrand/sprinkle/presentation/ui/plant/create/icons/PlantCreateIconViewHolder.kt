package fr.legrand.sprinkle.presentation.ui.plant.create.icons

import android.view.View
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_plant_create_icon_list_item.view.*

class PlantCreateIconViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(@DrawableRes iconRes: Int) {
        with(itemView) {
            view_plant_create_icon_list_item_image.setImageResource(iconRes)
        }
    }
}