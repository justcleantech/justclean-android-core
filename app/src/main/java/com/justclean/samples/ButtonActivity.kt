package com.justclean.samples

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.justclean.core.R
import com.justclean.core.setJcEnabled
import kotlinx.android.synthetic.main.activity_button.*


class ButtonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)
        jcBtn.setLifeCycleScope(this)
        jcBtn.setOnClickListener {
            jcBtn.setJcEnabled(false)
            jcBtn.withProgress(false)
            jcBtn.setJcText("7amada helal")
            jcBtn.setJcSecondary()
            showToast("Hamada mesh Helal")
        }

    }

    fun Context.showToast(txt:String){
        Toast.makeText(this,txt,Toast.LENGTH_LONG).show()
    }
}
