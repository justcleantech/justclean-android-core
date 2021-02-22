package com.justclean.uikit.atoms

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.justclean.uikit.R

class JCDigitEntry(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    val editText: TextInputEditText
    val inputLayout: TextInputLayout
    init {
        inflate(context, R.layout.digit_entry_layout, this)
        editText = findViewById(R.id.editText)
        inputLayout = findViewById(R.id.inputLayout)
    }
}