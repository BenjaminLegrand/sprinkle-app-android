package fr.legrand.sprinkle.presentation.ui.plant.list.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.presentation.ui.extensions.setOnClickDelayListener
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import kotlinx.android.synthetic.main.view_plant_list_item.view.view_plant_list_item_image
import kotlinx.android.synthetic.main.view_plant_list_item.view.view_plant_list_item_last_sprinkle_date
import kotlinx.android.synthetic.main.view_plant_list_item.view.view_plant_list_item_motion_layout
import kotlinx.android.synthetic.main.view_plant_list_item.view.view_plant_list_item_name
import kotlinx.android.synthetic.main.view_plant_list_item.view.view_plant_list_item_next_sprinkle_date
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlantListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(plant: PlantViewDataWrapper, onPlantClickListener: (Int) -> Unit) {
        with(itemView) {
            view_plant_list_item_name.text = plant.getName()
            view_plant_list_item_last_sprinkle_date.text =
                plant.getFormattedLastSprinkleDate(context)
            view_plant_list_item_next_sprinkle_date.text =
                plant.getFormattedNextSprinkleDate(context)

            setDeleteState(plant.deleting)

            Glide.with(context)
                .load(plant.getImageUrl())
                .error(R.drawable.ic_sunflower)
                .into(view_plant_list_item_image)

            setOnClickDelayListener { onPlantClickListener(plant.getId()) }
        }
    }

    fun setDeleteState(state: Boolean) {
        with(itemView) {
            if (state) {
                view_plant_list_item_motion_layout.transitionToEnd()
            } else {
                view_plant_list_item_motion_layout.transitionToStart()
            }
        }
    }
}