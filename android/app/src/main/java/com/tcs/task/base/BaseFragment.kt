package com.tcs.task.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Base class for all the fragments to handle common things
 */
abstract class BaseFragment<V : BaseViewModel<*>> :
    Fragment(), HasAndroidInjector {
    var baseActivity: BaseActivity<*>? = null
        private set
    private var mViewModel: V? = null
    private val mRootView: View? = null

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = viewModel
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mRootView
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            val activity = context
            baseActivity = activity
            activity.onFragmentAttached()
        }
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    val isNetworkConnected: Boolean
        get() = baseActivity != null && baseActivity!!.isNetworkConnected()


    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String?)
    }

    fun navigateBack(destination: Int) {
        if(destination == 0) {
            findNavController().popBackStack()
        } else {
            findNavController().popBackStack(destination, false)
        }
    }
}