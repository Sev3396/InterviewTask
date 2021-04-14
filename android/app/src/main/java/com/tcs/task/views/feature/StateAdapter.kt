package com.tcs.task.views.feature

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tcs.task.R
import com.tcs.task.data.manager.DataManager
import com.tcs.task.model.State
import kotlinx.android.synthetic.main.state_item_layout.view.*

class StateAdapter(dataManager: DataManager): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var stateList: List<State>
    private val mDataManager = dataManager
    fun setStates(states: List<State>) {
        stateList = states
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StateViewHolder(LayoutInflater.from(mDataManager.context)
            .inflate(R.layout.state_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Picasso.get().load(stateList[position].imageUrl).into(holder.itemView.stateLogo)
        holder.itemView.stateName.text = stateList[position].name
        holder.itemView.stateCapital.text = if(stateList[position].capital.isNullOrEmpty()) {
                stateList[position].hotSpot
            } else {
            stateList[position].capital
        }
        holder.itemView.setOnClickListener {
            val action = StateListingFragmentDirections.stateListToStateDetails(stateList[position])
            it.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return stateList.size
    }

    class StateViewHolder(view: View): RecyclerView.ViewHolder(view)
}
