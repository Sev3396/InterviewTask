package com.tcs.testapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tcs.testapp.R
import com.tcs.testapp.databinding.ItemStatecapitalsBinding
import com.tcs.testapp.listeners.StateCapitalItemClickListener
import com.tcs.testapp.model.State

class StateCapitalItemAdapter(private var arrStateDO: ArrayList<State>, private var domain: String) : RecyclerView.Adapter<StateCapitalItemAdapter.StateCapitalViewHolder>(),
    StateCapitalItemClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StateCapitalViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemStatecapitalsBinding>(inflater, R.layout.item_statecapitals, parent, false)
        return StateCapitalViewHolder(view)
    }

    override fun getItemCount(): Int = if (arrStateDO.size > 0) {
        arrStateDO.size
        } else 0

    override fun onBindViewHolder(holder: StateCapitalViewHolder, position: Int) {
        holder.view.domain = domain
        holder.view.stateDO = arrStateDO[position]
        holder.view.clickListener = this
    }

    class StateCapitalViewHolder(var view: ItemStatecapitalsBinding): RecyclerView.ViewHolder(view.root)

    override fun onClick(v: View) {
        if(v.tag != null) {
            val state = v.tag as State
            val actionClick = StatesnCapitalsFragmentDirections.actionListItemClick(state,domain)
            Navigation.findNavController(v).navigate(actionClick)
        }
    }

    fun refresh(arrStatesDO: List<State>?) {
        arrStateDO = arrStatesDO as ArrayList<State>
        notifyDataSetChanged()
    }

    fun setDomain(dom: String?) {
        domain = dom!!
    }
}