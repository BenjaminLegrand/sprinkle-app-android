package fr.legrand.sprinkle.presentation.ui.plant.list.item

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.legrand.sprinkle.R
import fr.legrand.sprinkle.presentation.ui.extensions.setOnClickDelayListener
import fr.legrand.sprinkle.presentation.ui.wrapper.PlantViewDataWrapper
import kotlinx.android.synthetic.main.view_plant_list_item.view.*
import kotlin.time.ExperimentalTime

private const val MAX_PROGRESS = 1f

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

            view_plant_list_item_motion_layout.addOnAttachStateChangeListener(
                object : View.OnAttachStateChangeListener {
                    override fun onViewAttachedToWindow(p0: View?) {
                        /*When detached from Window, MotionLayout seems to reset its state ->
                        When reattaching (every time we scroll, current view is reattached),
                        we simply set the currentState */
                        setDeleteState(
                            if (plant.deleting) PlantItemDeleteState.DELETING else PlantItemDeleteState.IDLE,
                            instantTransition = true
                        )
                    }

                    override fun onViewDetachedFromWindow(p0: View?) {
                        // Nothing to do
                    }
                }
            )
            setDeleteState(
                if (plant.deleting) PlantItemDeleteState.DELETING else PlantItemDeleteState.IDLE,
                instantTransition = true
            )

            Glide.with(context)
                .load(plant.getTemplateIconResource())
                .into(view_plant_list_item_image)

            setOnClickDelayListener { onPlantClickListener(plant.getId()) }
        }
    }

    fun setDeleteState(
        state: PlantItemDeleteState,
        instantTransition: Boolean = false
    ) {
        with(itemView) {
            when (state) {
                PlantItemDeleteState.IDLE ->
                    if (currentState == PlantItemDeleteState.DELETING) {
                        view_plant_list_item_motion_layout.setTransition(R.id.view_plant_list_item_transition_deleting_to_idle)
                    } else {
                        view_plant_list_item_motion_layout.setTransition(R.id.view_plant_list_item_transition_idle_to_idle)
                    }
                PlantItemDeleteState.DELETING ->
                    if (currentState == PlantItemDeleteState.IDLE) {
                        view_plant_list_item_motion_layout.setTransition(R.id.view_plant_list_item_transition_idle_to_deleting)
                    } else {
                        view_plant_list_item_motion_layout.setTransition(R.id.view_plant_list_item_transition_deleting_to_deleting)
                    }
                PlantItemDeleteState.DELETED ->
                    view_plant_list_item_motion_layout.setTransition(R.id.view_plant_list_item_transition_deleting_to_deleted)
            }

            if (instantTransition) {
                view_plant_list_item_motion_layout.progress = MAX_PROGRESS
            } else {
                view_plant_list_item_motion_layout.transitionToEnd()
            }
            currentState = state
        }
    }
}