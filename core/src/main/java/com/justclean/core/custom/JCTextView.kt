package com.justclean.core.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.os.Build
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.airbnb.paris.extensions.style
import com.airbnb.paris.utils.setPaddingBottom
import com.airbnb.paris.utils.setPaddingEnd
import com.airbnb.paris.utils.setPaddingStart
import com.airbnb.paris.utils.setPaddingTop
import com.justclean.core.R
import com.justclean.core.custom.JCTextView.JcTextGravity.*
import kotlinx.android.synthetic.main.jc_textview.view.*


class JCTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    var typedArray: TypedArray

    init {
        attrs.let {
            var tArray =
                context.obtainStyledAttributes(it, R.styleable.jc_textview_attributes, 0, 0)
            typedArray = tArray
            val spanStart = typedArray.getInteger(
                R.styleable.jc_textview_attributes_jc_spannable_start,
                0
            )
            val spanEnd = typedArray.getInteger(
                R.styleable.jc_textview_attributes_jc_spannable_end,
                0
            )
            val spanColor = typedArray.getResourceId(
                R.styleable.jc_textview_attributes_jc_spannable_color,
                0
            )


            val style = typedArray.getInt(
                R.styleable.jc_textview_attributes_jc_spannable_style,
                0
            )

            setSpannableStyle(
                spanStart,
                spanEnd,
                getSpannableStyle(style),
                ContextCompat.getColor(context, spanColor)
            )


        }
        typedArray.recycle()
    }

    fun setJCTextTheme(theme: Int = 0) {
        style {
            if (theme > 0) add(theme)
        }
    }


    fun setJcTextBackground(color: Int = 0) {
        if (color > 0) this.setBackgroundColor(
            getJcColor(color)
        )
    }



    fun setJcTextBackgroundTint(color: Int = 0) {
        if (color > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                this.background.colorFilter = BlendModeColorFilter(
                    getJcColor(color),
                    BlendMode.SRC_ATOP
                )
            } else {
                this.background.setColorFilter(
                    getJcColor(color), PorterDuff.Mode.SRC_ATOP
                )
            }
        }
    }



    fun setJcTextEndDrawable(drawable: Int = 0) {
        this.setCompoundDrawablesWithIntrinsicBounds(
            null, null, ContextCompat.getDrawable(
                context, drawable
            ), null
        )
    }


    fun setJcTextStartDrawable(drawable: Int = 0) {
        val jcTextViewDrawableStart = typedArray.getResourceId(
            R.styleable.jc_textview_attributes_jc_drawableStart,
            0
        )
        this.setCompoundDrawablesWithIntrinsicBounds(
            ContextCompat.getDrawable(
                context,
                jcTextViewDrawableStart
            ), null, null, null
        )
    }


    fun setJcTextElevation(elevation: Float = 0f) {
        this.elevation = elevation
    }


    fun setJcTextTypeFace(type: Int = 0) {
        if (type != null && type > 0) this.typeface = ResourcesCompat.getFont(context, type)
    }


    fun getTypeFace() = this.typeface

    fun setJcTextMaxLines(max: Int = 0) {
        if (max > 0) this.maxLines = max
    }


    fun setJcRotationX(rotation: Int = 0) {
        this.rotationX = rotation.toFloat()
    }



    fun setJCTextShadow(
        txtShadowDx: Int = 0,
        txtShadowDy: Int = 0,
        txtShadowColor: Int = 0,
        txtShadowRadius: Int = 0
    ) {

        this.setShadowLayer(
            txtShadowRadius.toFloat(), txtShadowDx.toFloat(), txtShadowDy.toFloat(),
            getJcColor(txtShadowColor)
        )
    }


    fun setJcText(txt: String = "") {
        this.text = txt
    }


    fun setJcTextColor(color: Int = 0) {
        this.setTextColor(getJcColor(color))
    }



    fun setJcTextSize(size: Int = 0) {
        this.setTextSize(TypedValue.COMPLEX_UNIT_PX, size.toFloat());
    }


    fun setJcTextPadding(
        jcPadding: Int = 0,
        jcPaddingStart: Int = 0,
        jcPaddingEnd: Int = 0,
        jcPaddingTop: Int = 0,
        jcPaddingBottom: Int = 0
    ) {

        this.apply {
            if (jcPadding > 0) setPadding(jcPadding, jcPadding, jcPadding, jcPadding)
            if (jcPaddingEnd > 0) setPaddingEnd(jcPaddingEnd)
            if (jcPaddingTop > 0) setPaddingTop(jcPaddingTop)
            if (jcPaddingBottom > 0) setPaddingBottom(jcPaddingBottom)
            if (jcPaddingStart > 0) setPaddingStart(jcPaddingStart)
        }
    }


    fun setJcTextGravity(jcGravity: JcTextGravity? = null) {

        when (jcGravity) {
            START -> this.gravity = Gravity.START
            END -> this.gravity = Gravity.END
            TOP -> this.gravity = Gravity.TOP
            BOTTOM -> this.gravity = Gravity.BOTTOM
            CENTER -> this.gravity = Gravity.CENTER
            CENTER_VERTICAL -> this.gravity = Gravity.CENTER_VERTICAL
            CENTER_HORIZONTAL -> this.gravity = Gravity.CENTER_HORIZONTAL

        }
    }


    fun setJcTextDirection(direction: JcTextDirections? = null) {

        when (direction) {
            JcTextDirections.LTR -> {
                this.textDirection = TextView.TEXT_DIRECTION_LTR
            }
            JcTextDirections.RTL -> {
                this.textDirection = TextView.TEXT_DIRECTION_RTL
            }
        }

    }


    fun setTxtAlignment(alignment: Int = 0) {
        if (alignment > 0) this.textAlignment = textAlignment
    }

    fun setSpannableStyle(start: Int, end: Int, style: JcTextSpannableStyle, color: Int) {

        try {
            val spannable = SpannableString(this.text)
            spannable.setSpan(
                ForegroundColorSpan(color),
                start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(
                StyleSpan(getSpannableStyle(style)),
                start, end,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            val textSize = resources.getDimensionPixelSize(R.dimen.xl_text_size)
            spannable.setSpan(AbsoluteSizeSpan(textSize), 8, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            this.text = spannable
        } catch (e: IndexOutOfBoundsException) {
            e.printStackTrace()
            Log.e("JCTextView", "setSpannableStyle: ${e.message}")
        }
    }

    private fun getSpannableStyle(style: JcTextSpannableStyle): Int {
        return when (style) {
            JcTextSpannableStyle.NORMAL -> 0
            JcTextSpannableStyle.BOLD -> 1
            JcTextSpannableStyle.ITALIC -> 2
            JcTextSpannableStyle.BOLD_ITALIC -> 3
        }
    }

    private fun getSpannableStyle(style: Int): JcTextSpannableStyle {
        return when (style) {
            0 -> JcTextSpannableStyle.NORMAL
            1 -> JcTextSpannableStyle.BOLD
            2 -> JcTextSpannableStyle.ITALIC
            3 -> JcTextSpannableStyle.BOLD_ITALIC
            else -> JcTextSpannableStyle.NORMAL
        }
    }

    fun getJcColor(jcColor: Int): Int {
        return ContextCompat.getColor(context, jcColor)
    }


    enum class JcTextGravity {
        CENTER, START, END, TOP, BOTTOM, CENTER_VERTICAL, CENTER_HORIZONTAL
    }

    enum class JcTextDirections {
        LTR, RTL
    }

    enum class JcTextSpannableStyle {
        NORMAL, BOLD, ITALIC, BOLD_ITALIC
    }
}