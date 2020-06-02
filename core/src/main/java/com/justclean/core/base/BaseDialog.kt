package com.justclean.core.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager


abstract class BaseDialog : DialogFragment() {
    var baseActivity: BaseActivity? = null
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context
        }
    }

    fun showDialogDismiss() {
        if (baseActivity != null) {
            baseActivity!!.showDialogDismiss()
        }
    }

    fun showMessage(message: String) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(message)
        }
    }

    fun showMessage(message: Int?) {
        if (baseActivity != null) {
            baseActivity!!.showMessage(message)
        }
    }


    fun isNetworkConnected(): Boolean {
        return if (baseActivity != null) {
            baseActivity!!.isNetworkConnected()
        } else false
    }


    fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }

    fun showSnackBar(res: Int) {
        if (baseActivity != null) {
            baseActivity!!.showSnackBar(res)
        }
    }

    fun showSnackBar(res: String) {
        if (baseActivity != null) {
            baseActivity!!.showSnackBar(res)
        }
    }

    fun showToast(res: Int) {
        if (baseActivity != null) {
            baseActivity!!.showToast(res)
        }
    }

    fun showToast(res: String) {
        if (baseActivity != null) {
            baseActivity!!.showToast(res)
        }
    }

    protected abstract fun bindViews(view: View)
    protected abstract fun setContentView(): Int
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // the content
        val root = RelativeLayout(activity)
        root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // creating the fullscreen dialog
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        if (dialog.window != null) {
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
        dialog.setCanceledOnTouchOutside(true)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(setContentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
    }

    override fun show(fragmentManager: FragmentManager, tag: String?) {
        try {
            val transaction = fragmentManager.beginTransaction()
            val prevFragment = fragmentManager.findFragmentByTag(tag)
            if (prevFragment != null) {
                transaction.remove(prevFragment)
            }
            transaction.addToBackStack(null)
            show(transaction, tag)
        } catch (e: IllegalStateException) {
            Log.d("TAG", e.printStackTrace().toString())
        }
    }

    fun dismissDialog(tag: String) {
        dismiss()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}