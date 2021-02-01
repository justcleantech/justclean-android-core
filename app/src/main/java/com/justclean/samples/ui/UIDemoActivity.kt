package com.justclean.samples.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.justclean.core.heplers.gone
import com.justclean.core.heplers.visible
import com.justclean.samples.R
import com.justclean.samples.databinding.ActivityUidemoBinding
import com.justclean.uikit.validation.ValidationObserver
import kotlinx.android.synthetic.main.activity_uidemo.*

class UIDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityUidemoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_uidemo)
        binding.observer = ValidationObserver()

        jcBtn.setOnClickListener {
            resetBtn.visible()
        }

        resetBtn.setOnClickListener {
            jcBtn.reset()
            it.gone()
        }

    }
}