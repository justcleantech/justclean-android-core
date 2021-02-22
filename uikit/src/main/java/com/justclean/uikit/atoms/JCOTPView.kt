package com.justclean.uikit.atoms

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View.OnKeyListener
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import com.justclean.uikit.R

class JCOTPView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    private val container: LinearLayout
    private val errorText: TextView
    private var digits: List<JCDigitEntry>
    private var isApplied = false
    var inputListeners: OTPViewListeners? = null

    init {
        inflate(context, R.layout.otp_view_layout, this)
        container = findViewById(R.id.containerLayout)
        errorText = findViewById(R.id.errorText)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.JCOTPView, 0, 0)
        val numOfDigits = typedArray.getInteger(R.styleable.JCOTPView_digits, 4)
        digits = getInputLayouts(numOfDigits)
        addNavigationHandler()
        typedArray.recycle()
    }

    private fun getInputLayouts(numOfDigits: Int): List<JCDigitEntry> {
        val digits = mutableListOf<JCDigitEntry>()
        repeat(numOfDigits) {
            val digit = JCDigitEntry(context)
            container.addView(digit)
            digits.add(digit)
        }
        return digits
    }

    private fun addNavigationHandler() {
        digits.forEachIndexed { index, jcDigitEntry ->
            jcDigitEntry.editText.setOnKeyListener { v, keyCode, event ->
                if (!isApplied) {
                    isApplied = true
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (!jcDigitEntry.editText.text.toString().isNullOrEmpty())
                            jcDigitEntry.editText.text?.clear()
                        goBackward(index)
                    } else if (keyCode in KeyEvent.KEYCODE_0..KeyEvent.KEYCODE_9) {
                        if (!jcDigitEntry.editText.text.toString().isNullOrEmpty())
                            jcDigitEntry.editText.setText(event.displayLabel.toString())
                        goForward(index)
                    }
                    removeError()
                } else {
                    isApplied = false
                }
                false
            }
        }
    }

    private fun goForward(index: Int) {
        val newIndex = index + 1
        if (newIndex != digits.size) {
            digits[newIndex].editText.requestFocus()
        } else {
            val otp = digits.joinToString("") { it.editText.text.toString() }
            inputListeners?.onInputEnd(otp)
        }
    }

    private fun goBackward(index: Int) {
        if (index != 0)
            digits[index - 1].requestFocus()
    }

    fun getOTP() = digits.joinToString("") { it.editText.text.toString() }

    fun setError(error: String? = null) {
        errorText.text = error
        digits.forEach {
            it.inputLayout.error = if (error.isNullOrBlank()) " " else error
        }
    }

    fun removeError(){
        digits.forEach {
            it.inputLayout.error = null
        }
        errorText.text = null
    }

    interface OTPViewListeners {
        fun onInputEnd(otp: String)
    }
}