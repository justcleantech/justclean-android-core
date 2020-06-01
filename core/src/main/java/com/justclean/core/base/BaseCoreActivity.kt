package com.justclean.core.base

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.justclean.core.R
import com.justclean.core.heplers.NetworkUtils
import com.justclean.core.heplers.showLongToast
import com.justclean.core.network.ConnectivityReceiver
import com.justclean.core.network.NetworkDialog
import com.mikepenz.materialize.util.KeyboardUtil
import kotlinx.android.synthetic.main.layout_toolbar.*


/**
 * @name Mohamed dawood
 * Copyrights (c) 2020-05-31 Created By Justclean Company
 */
abstract class BaseCoreActivity : AppCompatActivity(),
    ConnectivityReceiver.ConnectivityReceiverListener {
    private val TAG = "BaseActivity"
    private var connectivityReceiver: ConnectivityReceiver? = null
    private var isRunning: Boolean = false
    private val dialog = NetworkDialog.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initConnectivity()
//
//        val view = LayoutInflater.from(this).inflate(R.layout.layout_toolbar, null)
//        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
//        if (toolbar != null) {
//            setSupportActionBar(toolbar)
//            supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM;
//            supportActionBar!!.setCustomView(R.layout.layout_toolbar);
//            supportActionBar!!.title = "7mafa";
//            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
//            showToast("102")
//        }

    }


    fun showBack(show: Boolean): Boolean {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        return show
    }


    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        try {
            if (isRunning) {
                if (!isConnected) {
                    dialog.show(supportFragmentManager, "NetworkDialog")
                } else {
                    if (NetworkDialog.isShowing && dialog != null && dialog.isAdded) {
                        dialog.dismiss()
                        recreate()
                    }
                }
            }
        } catch (e: java.lang.NullPointerException) {
            Log.d("TAG", e.toString())
        } catch (e: IllegalStateException) {
            Log.d("TAG", e.toString())
        }
    }

    fun isNetworkConnected(): Boolean {
        return NetworkUtils.isNetworkConnected(this)
    }

    fun forceLogout(message: String) {
        showLongToast(message)
        //
    }

    fun hideKeyboard() {
        KeyboardUtil.hideKeyboard(this)
    }

    fun showToast(text: String) {
        val toast = Toast(this)
        val view = LayoutInflater.from(this).inflate(R.layout.layout_toast, null)
        val testMessage = view.findViewById<TextView>(R.id.textMessage)
        testMessage.text = text
        toast.view = view
        toast.duration = Toast.LENGTH_LONG
        toast.show()
    }

    fun showDialogDismiss() {
        //Not Used
    }

    fun showSnackBar(message: String) {
        val snackbar = Snackbar.make(layoutToolbar, message, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.error_red
            )
        )
        snackbar.show()
    }

    fun showSnackBar(message: Int) {
        val snackbar = Snackbar.make(layoutToolbar, message, Snackbar.LENGTH_LONG)
        snackbar.view.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.error_red
            )
        )
        snackbar.show()
    }

    fun showToast(message: Int) {
        showToast(getString(message))
    }

    fun showMessage(message: String) {
        if (message == "Forbidden") return
    }

    fun showMessage(message: Int?) {
        showMessage(getString(message!!))
    }

    override fun onResume() {
        try {
            super.onResume()
            isRunning = true
        } catch (e: java.lang.IllegalStateException) {
            Log.e(TAG, e.message)
        }
    }

    override fun onPause() {

        try {
            super.onPause()
            isRunning = false
        } catch (e: java.lang.IllegalStateException) {
            Log.e(TAG, "onPause: ${e.message}")
        }
    }

    private fun initConnectivity() {
        connectivityReceiver = ConnectivityReceiver()
        connectivityReceiver!!.setConnectivityReceiverListener(this)
        val connectivityIntentFilter = IntentFilter()
        connectivityIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(connectivityReceiver, connectivityIntentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            unregisterReceiver(connectivityReceiver)
        } catch (ignored: Exception) {
            Log.d(TAG, "onDestroy: ${ignored.message}")
        }
    }
}