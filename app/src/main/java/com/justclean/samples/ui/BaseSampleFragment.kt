package com.justclean.samples.ui

import android.os.Bundle
import com.justclean.core.base.BaseActivity
import com.justclean.core.base.LayoutRes
import com.justclean.samples.R

@LayoutRes(layout = R.layout.layout_testfragment_activity)
class BaseSampleFragment :BaseActivity(){


    override fun onActivityReady(savedInstanceState: Bundle?) {
        showToast("BaseSampleFragment")
    }
}