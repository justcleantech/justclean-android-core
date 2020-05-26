package com.justclean.core

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View.OnFocusChangeListener
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.airbnb.paris.extensions.style
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.jc_input_text.view.*


class JCEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {

    private var regex: Regex? = null
    var error = MutableLiveData<String>()
    var lifecycleOwner: LifecycleOwner? = null
    var text = ""
    var min = 0
    var max = 0
    var pattern = ""
    var isRegex = false
    var errorMessage = "error format"
    var maxErrorMsg = "error max"
    var minErrorMsg = "error min"


    init {
        LayoutInflater.from(context).inflate(R.layout.jc_input_text, this, true)
        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(it, R.styleable.jc_input_attributes, 0, 0)

            val inputStyle =
                typedArray.getResourceId(R.styleable.jc_input_attributes_input_style, 0)
            textInputLayout.style {
                add(inputStyle)
            }
            val hintColor =
                typedArray.getResourceId(R.styleable.jc_input_attributes_input_hint_color, 0)
            val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(context, hintColor))
            textInputLayout.hintTextColor = colorStateList
            val textInputColor =
                typedArray.getResourceId(R.styleable.jc_input_attributes_input_text_color, 0)
            jcEditText.setTextColor(ContextCompat.getColor(context, textInputColor))

            val editTextTheme =
                typedArray.getResourceId(R.styleable.jc_input_attributes_editText_theme, 0)
            jcEditText.style {
                add(editTextTheme)
            }
            val textSize =
                typedArray.getDimensionPixelSize(R.styleable.jc_input_attributes_text_size, 0)
            jcEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat());


            val endIconDrawable =
                typedArray.getResourceId(R.styleable.jc_input_attributes_end_icon_drawable, 0)
            textInputLayout.endIconDrawable = resources.getDrawable(endIconDrawable, null)

            val textCursorDrawable =
                typedArray.getDrawable(R.styleable.jc_input_attributes_text_cursor_drawable)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                jcEditText.textCursorDrawable = textCursorDrawable
            }
            val inputHintText =
                typedArray.getResourceId(R.styleable.jc_input_attributes_input_hint_text, 0)
            textInputLayout.hint = resources.getString(inputHintText)

            setInputType(typedArray.getString(R.styleable.jc_input_attributes_input_type) ?: "")

            val mode =
                TextInputMode.values()[typedArray.getInt(
                    R.styleable.jc_input_attributes_end_icon_mode,
                    0
                )]

            setEndIconMode(mode)
            val endIconTint =
                typedArray.getResourceId(R.styleable.jc_input_attributes_end_icon_tint, 0)
            setEndIconTint(endIconTint)

            val minChar = typedArray.getInteger(R.styleable.jc_input_attributes_min_chars, 1)
            val maxChar = typedArray.getInteger(R.styleable.jc_input_attributes_max_chars, 1000)
            setMaxChar(maxChar, maxErrorMsg)
            setMinChar(minChar, minErrorMsg)


            typedArray.recycle()
        }
    }

    fun setTextWatcher() {
        jcEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                //
                if (jcEditText.inputType == (InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)) {
                    getError()
                    if (!s.toString().isNullOrEmpty()) {
                        text = s.toString()
                        if (reg(text)) textInputLayout.error = null
                    }
                }
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("TTTTT", s.toString())
            }

        })
        jcEditText.onFocusChangeListener = OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (!reg(text)) {
                    if (isRegex)
                        error.postValue(errorMessage)
                    else  error.postValue("")
                } else {
                    if (text.isNotEmpty() && max > 0 && text.length > max) error.postValue(
                        maxErrorMsg
                    )
                    if (text.isNotEmpty() && min > 0 && text.length < min) error.postValue(
                        minErrorMsg
                    )
                }
            }
        }
    }

    fun getError() {
        if (lifecycleOwner != null)
            error.observe(lifecycleOwner!!, Observer {
                if (!it.isNullOrEmpty()) {
                    textInputLayout.error = it
                } else textInputLayout.error = null
            })
        else textInputLayout.error = null
    }

    fun reg(txt: String): Boolean {
        if (isRegex)
            return regex?.matches(txt)
                ?: Regex(pattern).matches(txt)
        return true
    }

    enum class TextInputMode {
        NONE, CLEAR_TEXT, CUSTOM, PASSWORD_TOGGLE, DROPDOWN_MENU
    }


    fun setInputStyle(style: Int) {
        textInputLayout.style {
            add(style)
        }
    }

    fun setHintColor(color: Int) {
        val colorStateList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
        textInputLayout.hintTextColor = colorStateList
    }

    fun setTextInputColor(color: Int) {
        jcEditText.setTextColor(ContextCompat.getColor(context, color))
    }

    fun setEditTextTheme(editTextTheme: Int) {
        jcEditText.style {
            add(editTextTheme)
        }
    }

    fun setJcTextSize(size: Int) {
        jcEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, size.toFloat());
    }

    fun setEndIconDrawable(drawable: Int) {
        textInputLayout.endIconDrawable = resources.getDrawable(drawable, null)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun setTextCursorDrawable(textCursorDrawable: Int) {
        jcEditText.textCursorDrawable = ContextCompat.getDrawable(context, textCursorDrawable)
    }

    fun setInputHintText(inputHintText: Int) {
        textInputLayout.hint = resources.getString(inputHintText)
    }

    fun setInputHintText(inputHintText: String) {
        textInputLayout.hint = inputHintText
    }

    fun setInputType(value: String) {
        when (value) {
            "text" -> jcEditText.inputType = InputType.TYPE_CLASS_TEXT
            "number" -> jcEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_NUMBER
            "phone" -> jcEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_PHONE
            "datetime", "date" -> jcEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_CLASS_DATETIME
            "textPassword" -> jcEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            "numberPassword" -> jcEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_NUMBER_VARIATION_PASSWORD
            "textWebPassword" -> jcEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_PASSWORD
            "textVisiblePassword" -> jcEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            "textEmailAddress" -> jcEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            "textEmailSubject" -> jcEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_SUBJECT
            "textWebEmailAddress" -> jcEditText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
            "textCapSentences", "textMultiLine" ->
                jcEditText.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or
                            InputType.TYPE_TEXT_FLAG_MULTI_LINE
        }
    }

    fun setEndIconMode(mode: TextInputMode) {
        when (mode) {
            TextInputMode.NONE -> {
                textInputLayout.endIconMode = TextInputLayout.END_ICON_NONE
            }
            TextInputMode.CLEAR_TEXT -> {
                textInputLayout.endIconMode = TextInputLayout.END_ICON_CLEAR_TEXT
            }
            TextInputMode.CUSTOM -> {
                textInputLayout.endIconMode = TextInputLayout.END_ICON_CUSTOM
            }
            TextInputMode.PASSWORD_TOGGLE -> {
                textInputLayout.endIconMode = TextInputLayout.END_ICON_PASSWORD_TOGGLE
            }
            TextInputMode.DROPDOWN_MENU -> {
                textInputLayout.endIconMode = TextInputLayout.END_ICON_DROPDOWN_MENU
            }
        }
    }

    fun setEndIconTint(endIconTint: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            jcEditText.background.colorFilter = BlendModeColorFilter(
                ContextCompat.getColor(context, endIconTint),
                BlendMode.SRC_ATOP
            )
        } else {
            jcEditText.background.setColorFilter(
                ContextCompat.getColor(context, endIconTint),
                PorterDuff.Mode.SRC_ATOP
            )
        }
    }

    fun setMinChar(min: Int, minErrorMsg: String) {
        this.minErrorMsg = minErrorMsg
        this.min = min

    }

    fun setMaxChar(max: Int, maxErrorMsg: String) {
        this.maxErrorMsg = maxErrorMsg
        this.max = max
    }

    fun withRegex(isRegex: Boolean, pattern: String, errorMessage: String) {
        this.errorMessage = errorMessage
        this.isRegex = isRegex
        if (isRegex)
            this.pattern = pattern
    }
}