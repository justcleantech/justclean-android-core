package com.justclean.core.base

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.justclean.core.R
import com.justclean.core.data.remote.AppSchedulerProvider
import com.justclean.core.data.remote.Model
import io.reactivex.disposables.CompositeDisposable
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException
import java.net.ConnectException

open abstract class BaseViewModel : ViewModel(), KoinComponent {

    val schedulerProvider: AppSchedulerProvider by inject()
    val compositeDisposable: CompositeDisposable by inject()
    val mContext: Context by inject()
    val showMainScreen = MutableLiveData<Boolean>()
    val showProgress = MutableLiveData<Boolean>()
    val showMessage = MutableLiveData<String>()
    val forceLogout = MutableLiveData<String>()
    val showError = MutableLiveData<String>()


    fun handleErrors(throwable: Throwable) {
        if (throwable is ConnectException) {
            showMessage.value = mContext.getString(R.string.api_connection_error)
            return
        }
        if (throwable is HttpException) {
            val builder = GsonBuilder().setPrettyPrinting();
            val gson: Gson = builder.create()
            try {
                //{"code":401,"status":false,"message":"Invalid PIN Or Device","data":null}
                val apiError: Model<*> = gson.fromJson(throwable.response()?.errorBody()!!.string(),
                    Model::class.java)
                if (apiError != null) {
                    handleError(apiError)
                } else {
                    showError.value = mContext.getString(R.string.api_default_error)
                    return
                }
            } catch (e: JsonSyntaxException) {
                showError.value = mContext.getString(R.string.api_default_error)
            } catch (e: NullPointerException) {
                showError.value = mContext.getString(R.string.api_default_error)
            }
        } else {
            showMessage.value = throwable.message!!
        }

    }

    abstract fun handleError(error:Model<*>)


    fun helloKoin() {
        Log.d("TestKoin", "helloKoin: ")
    }

    fun showLoadingProgress() {
        showProgress.value = true
    }

    fun hideLoadingProgress() {
        showProgress.value = false
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}