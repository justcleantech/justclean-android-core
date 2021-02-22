package com.justclean.uikit.atoms

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.widget.LinearLayout
import android.widget.TextView
import com.justclean.uikit.R
import com.justclean.uikit.views.OTPDigitEntry

class JCOTPView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    private val container: LinearLayout
    private val errorText: TextView
    private var digits: List<OTPDigitEntry>
    private var isApplied = false
    var inputListeners: OTPViewListeners? = null

    /**
     * Initiate the digits container and the error text
     * Fetch number of digits if settled or use 4 otherwise
     * Create the digits views and add it to the container
     * Add the navigation handler to control the typing flow
     */
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

    /**
     * Create digits based on the required number
     * Add the new digit view to the container layout
     * Append the digit to the digits list
     */
    private fun getInputLayouts(numOfDigits: Int): List<OTPDigitEntry> {
        val digits = mutableListOf<OTPDigitEntry>()
        repeat(numOfDigits) {
            val digit = OTPDigitEntry(context)
            container.addView(digit)
            digits.add(digit)
        }
        return digits
    }

    /**
     * Control the navigation for the OTP View
     * Go forward when new digit filled with number
     * Go backward when the digit number deleted
     * Remove the error style when start typing
     */
    private fun addNavigationHandler() {
        digits.forEachIndexed { index, jcDigitEntry ->
            jcDigitEntry.editText.setOnKeyListener { v, keyCode, event ->
                if (!isApplied) {
                    isApplied = true
                    if (keyCode == KeyEvent.KEYCODE_DEL) {
                        if (jcDigitEntry.editText.text.toString().isNotEmpty())
                            jcDigitEntry.editText.text?.clear()
                        goBackward(index)
                    } else if (keyCode in KeyEvent.KEYCODE_0..KeyEvent.KEYCODE_9) {
                        if (jcDigitEntry.editText.text.toString().isNotEmpty())
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

    /**
     * Used to navigate to the next digit if exist
     * by requesting focus for the new index
     * If latest index reached it will delegate the OTP to the listener
     */
    private fun goForward(index: Int) {
        val newIndex = index + 1
        if (newIndex != digits.size) {
            digits[newIndex].editText.requestFocus()
        } else {
            val otp = digits.joinToString("") { it.editText.text.toString() }
            inputListeners?.onInputEnd(otp)
        }
    }

    /**
     * Used to navigate to the previous OTP digit by
     * requesting focus for the previous index
     */
    private fun goBackward(index: Int) {
        if (index != 0)
            digits[index - 1].requestFocus()
    }

    /**
     * Return the entered OTP as string at anytime
     */
    fun getOTP() = digits.joinToString("") { it.editText.text.toString() }

    /**
     * Set error style to the OTP view and display error message
     */
    fun setError(error: String? = null) {
        errorText.text = error
        digits.forEach {
            it.inputLayout.error = if (error.isNullOrBlank()) " " else error
        }
    }

    /**
     * Reset the OTP view to the normal appearance
     * Usually called when user start typing
     */
    fun removeError(){
        digits.forEach {
            it.inputLayout.error = null
        }
        errorText.text = null
    }

    /**
     * Interface to notify the users when otp entry reached the last digit
     */
    interface OTPViewListeners {
        fun onInputEnd(otp: String)
    }
}