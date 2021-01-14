package fr.legrand.sprinkle.presentation.ui.plant.list.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.presentation.ui.extensions.onTransitionEnd
import fr.legrand.sprinkle.presentation.ui.extensions.setOnClickDelayListener
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import kotlinx.android.synthetic.main.view_plant_list_item.view.*
import kotlin.time.ExperimentalTime

private const val MAX_PROGRESS = 1f

@ExperimentalTime
class PlantListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(plant: PlantViewDataWrapper, onPlantClickListener: (Int) -> Unit) {
        with(itemView) {
            view_plant_list_item_name.text = plant.getName()
            view_plant_list_item_last_sprinkle_date.text =
                plant.getFormattedLastSprinkleDate(context)
            view_plant_list_item_next_sprinkle_date.text =
                plant.getFormattedNextSprinkleDate(context)

            setDeleteState(
                if (plant.deleting) PlantItemDeleteState.DELETING else PlantItemDeleteState.IDLE,
                instantTransition = true
            )

            Glide.with(context)
                .load(plant.getImageUrl())
                .error(R.drawable.ic_sunflower)
                .into(view_plant_list_item_image)

            setOnClickDelayListener { onPlantClickListener(plant.getId()) }
        }
    }

    fun setDeleteState(
        state: PlantItemDeleteState,
        instantTransition: Boolean = false,
        onTransitionEnd: () -> Unit = {}
    ) {
        with(itemView) {
            when (state) {
                PlantItemDeleteState.IDLE ->
                    view_plant_list_item_motion_layout.transitionToState(R.id.view_plant_list_item_state_idle)
                PlantItemDeleteState.DELETING ->
                    view_plant_list_item_motion_layout.transitionToState(R.id.view_plant_list_item_state_deleting)
                PlantItemDeleteState.DELETED ->
                    view_plant_list_item_motion_layout.transitionToState(R.id.view_plant_list_item_state_deleted)
            }

            if (instantTransition) {
                view_plant_list_item_motion_layout.progress = MAX_PROGRESS
            }

            view_plant_list_item_motion_layout.onTransitionEnd {
                onTransitionEnd()
            }
        }
    }
}