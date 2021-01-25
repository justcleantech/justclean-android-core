package com.justclean.core.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.justclean.core.R
import com.justclean.core.heplers.gone
import com.justclean.core.heplers.visible
import kotlinx.android.synthetic.main.activity_button.*
import kotlinx.android.synthetic.main.activity_button.jcBtn
import kotlinx.android.synthetic.main.activity_uidemo.*

class UIDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uidemo)
        jcBtn.setOnClickListener {
            resetBtn.visible()
        }

        resetBtn.setOnClickListener {
            jcBtn.reset()
            it.gone()
        }

        enable.setOnClickListener {
            jcBtn.setButtonEnabled(true)
        }

        disable.setOnClickListener {
            jcBtn.setButtonEnabled(false)
        }
    }
}