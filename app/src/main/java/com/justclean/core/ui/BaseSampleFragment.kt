package com.justclean.core.ui

import android.os.Bundle
import com.justclean.core.R
import com.justclean.core.base.BaseActivity
import com.justclean.core.base.LayoutRes


@LayoutRes(layout = R.layout.layout_testfragment_activity)
class BaseSampleFragment :BaseActivity(){


    override fun onActivityReady(savedInstanceState: Bundle?) {
        showToast("BaseSampleFragment")
    }
}