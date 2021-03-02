package com.justclean.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.justclean.sample.databinding.ActivityMainBinding
import com.justclean.uikit.atoms.JCOTPView
import com.justclean.uikit.validation.ValidationObserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val validationObserver = ValidationObserver()
        validationObserver.countryCode = "QA"
        validationObserver.isPhoneValid(phone.text.toString())
        validationObserver.setFieldText("phone", "65159298")
        validationObserver.setFieldText("email", "ab@jc.co")
        binding.observer = validationObserver
        setUpOTPListener()
        submit.setOnClickListener {
            otpView.setError(phone.text.toString())
            otpView.clearOtp()
        }
    }

    private fun setUpOTPListener() {
        otpView.inputListeners = object : JCOTPView.OTPViewListeners {
            override fun onInputEnd(otp: String) {
                Toast.makeText(this@MainActivity, otp, Toast.LENGTH_SHORT).show()
            }
        }
    }
}