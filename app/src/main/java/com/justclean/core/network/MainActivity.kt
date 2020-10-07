package com.justclean.core.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.justclean.core.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dispose = ApiManagerRepository.getLanguages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result.text = it.data[0].name
            }, {
                result.text = it.message
            })
    }
}
