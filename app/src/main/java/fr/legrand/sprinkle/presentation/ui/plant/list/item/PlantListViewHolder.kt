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

@ExperimentalTime
class PlantListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var currentState = PlantItemDeleteState.IDLE

    fun bind(plant: PlantViewDataWrapper, onPlantClickListener: (Int) -> Unit) {
        with(itemView) {
            view_plant_list_item_name.text = plant.getName()
            view_plant_list_item_last_sprinkle_date.text =
                plant.getFormattedLastSprinkleDate(context)
            view_plant_list_item_next_sprinkle_date.text =
                plant.getFormattedNextSprinkleDate(context)

            val newState = if (plant.deleting) PlantItemDeleteState.DELETING else PlantItemDeleteState.IDLE
            if (newState != currentState) {
                setDeleteState(newState)
            }

            Glide.with(context)
                .load(plant.getImageUrl())
                .error(R.drawable.ic_sunflower)
                .into(view_plant_list_item_image)

            setOnClickDelayListener { onPlantClickListener(plant.getId()) }
        }
    }

    fun setDeleteState(state: PlantItemDeleteState, onTransitionEnd: () -> Unit = {}) {
        with(itemView) {
            when (state) {
                PlantItemDeleteState.IDLE ->
                    if (currentState == PlantItemDeleteState.DELETED) {
                        view_plant_list_item_motion_layout.setTransition(R.id.view_plant_list_item_transition_deleted_to_idle)
                    } else {
                        view_plant_list_item_motion_layout.setTransition(R.id.view_plant_list_item_transition_deleting_to_idle)
                    }
                PlantItemDeleteState.DELETING ->
                    view_plant_list_item_motion_layout.setTransition(R.id.view_plant_list_item_transition_idle_to_deleting)
                PlantItemDeleteState.DELETED ->
                    view_plant_list_item_motion_layout.setTransition(R.id.view_plant_list_item_transition_deleting_to_deleted)
            }
            view_plant_list_item_motion_layout.transitionToEnd()
            currentState = state

            view_plant_list_item_motion_layout.onTransitionEnd {
                onTransitionEnd()
            }
        }
    }
}