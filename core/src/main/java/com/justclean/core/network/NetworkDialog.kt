package com.justclean.core.network

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.justclean.core.R
import com.justclean.core.base.BaseCoreActivity
import com.justclean.core.base.BaseDialog
import kotlinx.android.synthetic.main.dialog_network.*

class NetworkDialog : BaseDialog() {

    companion object {
        var isShowing = false
        fun newInstance(): NetworkDialog {
            return NetworkDialog()
        }
    }

    override fun bindViews(view: View) {
        actionRetry.setOnClickListener {
            if (isNetworkConnected()) {
                dismiss()
                startActivity(Intent(context!!, BaseCoreActivity::class.java))
            }
        }
        isShowing = true
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        isShowing = false
    }

    override fun setContentView(): Int {
        return R.layout.dialog_network
    }

     fun onNetworkChange(isConnected: Boolean) {
        if (isConnected) {
            dismiss()
        }
    }
}