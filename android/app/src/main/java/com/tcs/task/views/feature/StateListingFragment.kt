package com.tcs.task.views.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcs.task.R
import com.tcs.task.base.BaseFragment
import com.tcs.task.model.State
import kotlinx.android.synthetic.main.state_listing_fragment.*
import javax.inject.Inject

class StateListingFragment: BaseFragment<TaskViewModel>() {

    @Inject
    lateinit var taskViewModel: TaskViewModel
    @Inject
    lateinit var stateAdapter: StateAdapter
    override val viewModel: TaskViewModel
        get() = taskViewModel


    private val stateListingObserver = Observer<List<State>> {
        stateList ->
        stateAdapter.setStates(stateList)
        stateAdapter.notifyDataSetChanged()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.state_listing_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stateListing.layoutManager = LinearLayoutManager(requireContext())
        stateListing.adapter = stateAdapter
        taskViewModel.getStateList()
        taskViewModel.getStateData().observe(requireActivity(), stateListingObserver)
    }
}