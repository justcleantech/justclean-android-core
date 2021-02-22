package com.justclean.uikit.atoms

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View.OnKeyListener
import android.widget.LinearLayout
import androidx.core.widget.doBeforeTextChanged
import androidx.core.widget.doOnTextChanged
import com.justclean.uikit.R

class JCOTPView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    private val container: LinearLayout
    private var digits: List<JCDigitEntry>
    var inputListeners: OTPViewListeners? = null

    init {
        inflate(context, R.layout.otp_view_layout, this)
        container = findViewById(R.id.containerLayout)
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

            jcDigitEntry.editText.doBeforeTextChanged { text, start, count, after ->

            }

            jcDigitEntry.editText.doOnTextChanged { text, start, before, count ->
                if (text.isNullOrEmpty())
                    goBackward(index)
                else
                    goForward(index)
            }
//            jcDigitEntry.editText.doAfterTextChanged {
//                if (it.isNullOrEmpty())
//                    goBackward(index)
//                else
//                    goForward(index)
//            }
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

    interface OTPViewListeners {
        fun onInputEnd(otp: String)
    }
}