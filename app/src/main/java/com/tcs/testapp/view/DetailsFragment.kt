package com.tcs.testapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tcs.testapp.R
import com.tcs.testapp.databinding.FragmentDetailsBinding
import com.tcs.testapp.model.State

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailsFragment : Fragment() {

    var state: State? = null
    var domain: String? = null
    private lateinit var dataBinding: FragmentDetailsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            state = DetailsFragmentArgs.fromBundle(it).state
            domain = DetailsFragmentArgs.fromBundle(it).domain
        }
        dataBinding.state = state
        dataBinding.domain = domain
    }
}