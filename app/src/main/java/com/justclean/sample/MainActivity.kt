package com.justclean.sample

import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.justclean.sample.databinding.ActivityMainBinding
import com.justclean.uikit.validation.ValidationObserver
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val validationObserver = ValidationObserver()
        validationObserver.countryCode = "QA"
        validationObserver.isPhoneValid(phone.text.toString())
        binding.observer = validationObserver
    }
}