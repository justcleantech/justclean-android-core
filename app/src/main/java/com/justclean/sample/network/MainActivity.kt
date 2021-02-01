package com.justclean.sample.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.justclean.core.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val languagesViewModel: LanguagesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        getLanguagesByRxJava()
        languagesViewModel.languages.observe(this, Observer {
            result.text = it.data.first().name
        })
    }

    private fun getLanguagesByRxJava() {
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
