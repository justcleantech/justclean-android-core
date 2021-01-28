package com.justclean.core.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.justclean.core.R
import com.justclean.core.databinding.ActivityUidemoBinding
import com.justclean.core.heplers.gone
import com.justclean.core.heplers.visible
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