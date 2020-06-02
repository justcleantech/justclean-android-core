package com.justclean.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.justclean.core.R
import com.justclean.core.heplers.NetworkUtils


abstract class BaseFragment : Fragment() {

    @StringRes
    var title: Int = R.string.app_name

    var baseActivity: BaseActivity? = null
        private set
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        withTitle(getLayoutRes().titleId)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context)
            .inflate((this::class.java.annotations.find { it is LayoutRes } as? LayoutRes)!!.layout,
                container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onFragmentReady(view, savedInstanceState)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context as BaseActivity?
            this.baseActivity = activity
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    fun showLoading() {
        //not used yet
    }

    fun hideLoading() {
        //not used yet
    }

    fun showErrorMessage(){
        //not used yet
    }

    fun isConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(requireContext())
    }

    open fun onNetworkChange(isConnected: Boolean) {
        baseActivity!!.onNetworkConnectionChanged(isConnected)
    }

    private fun getLayoutRes(): LayoutRes {
        try {
            return (this::class.java.annotations.find { it is LayoutRes } as? LayoutRes)!!
        } catch (e: Exception) {
            throw KotlinNullPointerException(
                "Please add the @LayoutRes annotation on the top of the fragment or check your layout if its available")
        }
    }
    fun withTitle(title: Int) {
        this.title = title
    }

    abstract fun onFragmentReady(view: View, bundle: Bundle?)


}