package com.tcs.task.base

import android.annotation.TargetApi
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.jet2.blog.utils.NetworkUtils
import dagger.android.support.DaggerAppCompatActivity
import java.util.ArrayList

abstract class BaseActivity<V : BaseViewModel<*>> : DaggerAppCompatActivity(), BaseFragment.Callback {

    private val mViewModel: V? = null

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkNetworkAvailability()
    }


    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    fun checkForPermissions(permissions: Array<String>, permissionRequestCode: Int): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var result: Int
            val listPermissions = ArrayList<String>()
            for (permission in permissions) {
                result = ContextCompat.checkSelfPermission(this, permission)
                if (result != PackageManager.PERMISSION_GRANTED) {
                    listPermissions.add(permission)
                }
            }
            if (!listPermissions.isEmpty()) {
                requestPermissionsSafely(
                    listPermissions.toTypedArray<String>(),
                    permissionRequestCode
                )
                return false
            }
        }
        return true
    }


    companion object {

        val TAG = BaseActivity::class.java.name
    }


    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkAvailable(applicationContext)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onFragmentAttached() {}

    override fun onFragmentDetached(tag: String?) {}

    /**
     * Function: showToast will show message in Toast
     */
    fun showToast(resId: Int) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show()

    }

    /**
     * Function : checkNetworkAvailability for checking
     * network availability on Android P and above OS version
     */
    fun checkNetworkAvailability() {
        //TODO: Add this once you have api calls for server.
    }


}
