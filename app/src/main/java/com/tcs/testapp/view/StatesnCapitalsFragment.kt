package com.tcs.testapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tcs.testapp.R
import com.tcs.testapp.viewmodel.StateCapitalsViewModel
import kotlinx.android.synthetic.main.fragment_statecapitals.*

/**
 * This fragment is to display list of states and its capitals.
 */
class StatesnCapitalsFragment : Fragment() {

    private lateinit var viewModel: StateCapitalsViewModel
    private val listAdapter = StateCapitalItemAdapter(arrayListOf(),"")

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statecapitals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(StateCapitalsViewModel::class.java)
        if(viewModel.stateCapitals.value == null)
            viewModel.getStateCapitals()
        initObservers()

        rv_StateCapitals.apply {
            val layoutMngr = LinearLayoutManager(context)
            layoutMngr.orientation = RecyclerView.VERTICAL
            layoutManager = layoutMngr
            adapter = listAdapter
        }

    }

    /**
     * This method is to initialize the Observers required in this class
     */
    private fun initObservers(){

        viewModel.stateCapitals.observe(viewLifecycleOwner) {
            if(it != null) {
                tv_NoDataFound.visibility = View.GONE
                pb_Progress.visibility = View.GONE
                rv_StateCapitals.visibility = View.VISIBLE
                it.domain?.let { it1 -> listAdapter.setDomain(it1) }
                it.state?.let { it1 -> listAdapter.refresh(it1)}
            }
            else{
                tv_NoDataFound.visibility = View.VISIBLE
                rv_StateCapitals.visibility = View.GONE
                pb_Progress.visibility = View.GONE
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            pb_Progress.visibility = if(it) View.VISIBLE else View.GONE
            if(it) {
                tv_NoDataFound.visibility = View.GONE
                rv_StateCapitals.visibility = View.GONE
            }
        }
        viewModel.loadError.observe(viewLifecycleOwner) {
            tv_NoDataFound.visibility = if(it) View.VISIBLE else View.GONE
            if(it) {
                rv_StateCapitals.visibility = View.GONE
                pb_Progress.visibility = View.GONE
            }
        }


    }
}