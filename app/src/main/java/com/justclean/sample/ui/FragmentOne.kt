package com.justclean.sample.ui

import android.os.Bundle
import android.view.View
import com.justclean.core.R
import com.justclean.core.base.BaseFragment
import com.justclean.core.base.LayoutRes
import com.justclean.core.heplers.showShortToast

@LayoutRes(layout = R.layout.fragmnet_test)
class FragmentOne :BaseFragment(){
    override fun onFragmentReady(view: View, bundle: Bundle?) {
        baseActivity?.showShortToast("is network connected => ${isConnected()}")
    }

    override fun onNetworkChange(isConnected: Boolean){
        //
    }
}