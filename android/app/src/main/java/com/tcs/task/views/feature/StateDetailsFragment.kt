package com.tcs.task.views.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.tcs.task.R
import com.tcs.task.base.BaseFragment
import kotlinx.android.synthetic.main.state_details_fragment.*
import javax.inject.Inject

class StateDetailsFragment: BaseFragment<TaskViewModel>() {

    @Inject
    lateinit var taskViewModel: TaskViewModel
    override val viewModel: TaskViewModel
        get() = taskViewModel

    private val args: StateDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.state_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(args.stateDetails?.imageUrl).into(stateDetailsImage)
        stateDetailsName.text = args.stateDetails?.name
        stateDetailsCapital.text = if(args.stateDetails?.capital.isNullOrEmpty()) {
            args.stateDetails?.hotSpot
        } else {
            args.stateDetails?.capital
        }
    }
}