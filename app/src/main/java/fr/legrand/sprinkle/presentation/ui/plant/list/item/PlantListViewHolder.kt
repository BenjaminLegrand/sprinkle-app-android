package fr.legrand.sprinkle.presentation.ui.plant.list.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import kotlinx.android.synthetic.main.view_plant_list_item.view.*
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlantListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(plant: PlantViewDataWrapper, onPlantClickListener: (Int) -> Unit, deleted: Boolean) {
        with(itemView) {
            view_plant_list_item_name.text = plant.getName()
            view_plant_list_item_last_sprinkle_date.text =
                plant.getFormattedLastSprinkleDate(context)
            view_plant_list_item_next_sprinkle_date.text =
                plant.getFormattedNextSprinkleDate(context)

            if (deleted) {
                // TODO delete UI
            }

            Glide.with(context)
                .load(plant.getImageUrl())
                .error(R.drawable.ic_sunflower)
                .into(view_plant_list_item_image)

            setOnClickListener {
                onPlantClickListener(plant.getId())
            }
        }
    }
}