package com.justclean.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import com.justclean.networking.NetworkController
import com.justclean.networking.RequestBody
import com.justclean.networking.RequestType
import com.justclean.sample.databinding.ActivityMainBinding
import com.justclean.uikit.atoms.JCOTPView
import com.justclean.uikit.validation.ValidationObserver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val validationObserver = ValidationObserver()
//        validationObserver.countryCode = "QA"
//        validationObserver.isPhoneValid(phone.text.toString())
//        validationObserver.setFieldText("phone", "65159298")
//        validationObserver.setFieldText("email", "ab@jc.co")
//        binding.observer = validationObserver
//        setUpOTPListener()
//        submit.setOnClickListener {
//            otpView.setError(phone.text.toString())
//            otpView.clearOtp()
//        }
        init()

    }

    private fun init(){
        val interceptors = arrayListOf<Interceptor>()
        interceptors.add(Interceptor { chain ->
            val original = chain.request()
            val builder = original.newBuilder()
            builder.header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiItTWJXWkd3VzN5RGhhZVNtdm55VyIsImFsZyI6IlJTMjU2IiwiaWF0IjoxNjI4NjkyODMxLCJpc19hZG1pbiI6ZmFsc2UsImlzX2psbSI6ZmFsc2UsImlzX3VzZXIiOnRydWUsImlzX2NvcnBvcmF0ZSI6ZmFsc2UsImlzX2RlbGl2ZXJ5X3VzZXIiOmZhbHNlLCJleHAiOjE2OTE4MDgwMzF9.TPA27tOGTMCQm2BU1Mluh0vYQP-P4c9jRIhe2g9zA2I")
                .header("country_id", "1")
                .header("group_id", "1")
                .header("device_token",  "d69hSSzGQ5mtcwIFSvnxQb:APA91bH6SwO1eaIgpYhsxGtTOYsSjreONVI-FZcYgJLivP1gzW_751tJSgiLXhqVb2VtmIeYahbqaGff6SWrSfjBzh0AurA7ZlXX22Jp67O_Y24oqbFoKlfp66XHEfXB5Yjx8JZaDE9U")
                .header("advertising_id", "8c28a465-2b0d-4f94-84c1-980b1c2c8fc2")
                .header("language_id", "1")
                .header("X-Api-Key", "IFoaGuJm94JRE3f/y+4pMA==:p4XrKu6bmjG04UFmJelOySaiYhtsQBu0mjKy0y8pBVouckPww7X+AJP2aaziDc55DQzZHgL5u9SDo9r5U0IQ7EVwj7Im3wClsurIBduZOwNOXF3gDKV4rzsZCWNySz+kuUx2nLguS+R1A3zFqx5HHxeWGs2/2f7QWODOQUlp02i3ewRK8Oad2v4t/LB5rQlEUl2Kc20EskVQ0A+NX+VRVUrOGhMy+6NspEnPaZExuFC6tgHLfQDxFRu7epdDA3QSXFObSG2MI4OcNOZBZ9Cge0+7fKIMzVxSMPkzEqShfENLGHr8ZLSOdG7ct8IC/CB7w/y7RmTJhVTKeGEP7aiCWv9RrNWidQN1vrYe01cnXfALywjX03qzsLevfU1u9a8ZDX6blwftjf4ekUK+xmGBYNFYBuW4kJE+NBfYLtI+8Aj7GXRfyiVzh5TLImOTgX9bQKuF9VaBEfoT7V7s5tvBQR/HzJ5CpKj72ojQOsyFE3NSSFXr0XNWzdBm+dJpFJpMex4uVChI1FtDOc+N5Tq6oiQpT1LoZPV0m08PRy69DVYIWXszLno2XZ5SpDmOoMdvCv1DyVA0/HS+DzVrxOdFVQ==")
            val request = builder.method(original.method, original.body).build()
            return@Interceptor chain.proceed(request)
        })
        if (BuildConfig.DEBUG) {
            interceptors.add(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
       val eee= NetworkController.init(interceptors,0,0)

        GlobalScope.launch(Dispatchers.IO) {
            val w = eee.processRequest<Any>("user-schedules/1639603", RequestType.DELETEBody(body = NewCancelOrderRequest(
                1,"wdwdwd",1639603,false
            )))
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


data class NewCancelOrderRequest(
    val reasonId: Int,
    val reason: String,
    val orderId: Int,
    val hasNote: Boolean
) : RequestBody