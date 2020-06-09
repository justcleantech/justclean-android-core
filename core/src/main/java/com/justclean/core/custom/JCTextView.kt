package com.justclean.core.custom

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.Typeface.BOLD
import android.graphics.drawable.Drawable
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
import android.view.LayoutInflater
import android.widget.FrameLayout
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
import java.lang.reflect.InvocationTargetException


class JCTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {
    var typedArray: TypedArray

    init {
        LayoutInflater.from(context).inflate(R.layout.jc_textview, this, true)
        attrs.let {
            var tArray   =
                context.obtainStyledAttributes(it, R.styleable.jc_textview_attributes, 0, 0)
            typedArray = tArray
            setJcTextBackgroundTint()
            setJcTextStartDrawable()
            setJcTextEndDrawable()
            setJcTextBackground()
            setJcTextDirection()
            setJcTextElevation()
            setJcTextTypeFace()
            setJcTextMaxLines()
            setJcTextPadding()
            setJcTextGravity()
            setTxtAlignment()
            setJCTextShadow()
            setJCTextTheme()
            setJcTextColor()
            setJcRotationX()
            setJcRotaionY()
            setJcTextSize()
            setJcText()
            val spanStart = typedArray.getInteger(
                R.styleable.jc_textview_attributes_jc_textview_spannable_start,
                0
            )
            val spanEnd = typedArray.getInteger(
                R.styleable.jc_textview_attributes_jc_textview_spannable_end,
                0
            )
            val spanColor = typedArray.getResourceId(
                R.styleable.jc_textview_attributes_jc_textview_spannable_color,
                0
            )


            val style = typedArray.getInt(
                R.styleable.jc_textview_attributes_jc_textview_spannable_style,
                0
            )

            setSpannableStyle(
                spanStart,
                spanEnd,
                getSpannableStyle(style),
                ContextCompat.getColor(context, spanColor)
            )


        }
     //   typedArray.recycle()
    }

    fun setJCTextTheme(theme: Int = 0) {
        val jcTextViewTheme =
            typedArray.getResourceId(R.styleable.jc_textview_attributes_jc_textview_theme, 0)
        val tempTheme = if (theme > 0) theme else jcTextViewTheme
        style {
            if (tempTheme > 0) add(tempTheme)
        }
    }

    fun setJcTextBackground(color: Int = 0) {
        val jcTextViewBackground =
            typedArray.getResourceId(
                R.styleable.jc_textview_attributes_jc_textview_background,
                0
            )
        val tempColor = if (color > 0) color else jcTextViewBackground
        if (tempColor > 0) jcTextview.setBackgroundColor(
            getJcColor(tempColor)
        )
    }

    fun setJcTextBackgroundTint(color: Int = 0) {
        val jcTextViewBackgroundTint =
            typedArray.getResourceId(
                R.styleable.jc_textview_attributes_jc_textview_backgroundTint,
                0
            )

        val tempColor = if (color > 0) color else jcTextViewBackgroundTint
        if (tempColor > 0) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                jcTextview.background.colorFilter = BlendModeColorFilter(
                    getJcColor(tempColor),
                    BlendMode.SRC_ATOP
                )
            } else {
                jcTextview.background.setColorFilter(
                    getJcColor(tempColor), PorterDuff.Mode.SRC_ATOP
                )
            }
        }
    }

    fun setJcTextEndDrawable(drawable: Int = 0) {
        val jcTextViewDrawableEnd = typedArray.getResourceId(
            R.styleable.jc_textview_attributes_jc_textview_drawableEnd,
            0
        )
        val tempImg = if (drawable > 0) drawable else jcTextViewDrawableEnd
        val img: Drawable =
            context.resources.getDrawable(tempImg)
        jcTextview.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null)
    }

    fun setJcTextStartDrawable(drawable: Int = 0) {
        val jcTextViewDrawableStart = typedArray.getResourceId(
            R.styleable.jc_textview_attributes_jc_textview_drawableStart,
            0
        )

        val tempImg = if (drawable > 0) drawable else jcTextViewDrawableStart
        val img: Drawable =
            context.resources.getDrawable(tempImg)

        jcTextview.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null)
    }

    fun setJcTextElevation(jcElevation: Float = 0f) {
        val elevation =
            typedArray.getDimension(R.styleable.jc_textview_attributes_jc_textview_elevation, 0f)
        val tempElevation = if (jcElevation > 0) jcElevation else elevation
        jcTextview.elevation = tempElevation
    }

    fun setJcTextTypeFace(type: Int = 0) {
        val typeFace = typedArray.getResourceId(
            R.styleable.jc_textview_attributes_jc_textview_fontFamily,
            0
        )
        val tempTypeFace = if (type > 0) type else typeFace
        if (tempTypeFace != null && tempTypeFace > 0) jcTextview.typeface =
            ResourcesCompat.getFont(context, tempTypeFace)
    }

    fun getTypeFace() = jcTextview.typeface

    fun setJcTextMaxLines(max: Int = 0) {
        val maxLines = typedArray.getInteger(
            R.styleable.jc_textview_attributes_jc_textview_maxLines, 0
        )
        val tempMax = if (max > 0) max else maxLines
        if (tempMax > 0) jcTextview.maxLines = tempMax
    }

    fun setJcRotationX(rotation: Int = 0) {
        val rotationX = typedArray.getInteger(
            R.styleable.jc_textview_attributes_jc_textview_rotationX, 0
        )
        val tempRotation = if (rotation > 0) rotation else rotation
        jcTextview.rotationX = tempRotation.toFloat()
    }

    fun setJcRotaionY(rotation: Int = 0) {
        val rotationY = typedArray.getInteger(
            R.styleable.jc_textview_attributes_jc_textview_rotationY, 0
        )
        val tempRotation = if (rotation > 0) rotation else rotationY
        jcTextview.rotationY = tempRotation.toFloat()
    }

    fun setJCTextShadow(
        txtShadowDx: Int = 0,
        txtShadowDy: Int = 0,
        txtShadowColor: Int = 0,
        txtShadowRadius: Int = 0
    ) {
        val shadowDX = typedArray.getInteger(
            R.styleable.jc_textview_attributes_jc_textview_shadowDx, 0
        )
        val shadowDY = typedArray.getInteger(
            R.styleable.jc_textview_attributes_jc_textview_shadowDY, 0
        )

        val shadowColor = typedArray.getResourceId(
            R.styleable.jc_textview_attributes_jc_textview_shadowColor, 0
        )

        val shadowRadius = typedArray.getInteger(
            R.styleable.jc_textview_attributes_jc_textview_shadowRadius, 0
        )
        val tempShadowDx = if (txtShadowDx != 0) txtShadowDx else shadowDX
        val tempShadowDy = if (txtShadowDx != 0) txtShadowDy else shadowDY
        val tempShadowColor = if (txtShadowColor != 0) txtShadowColor else shadowColor
        val tempShadowRadius = if (txtShadowRadius != 0) txtShadowRadius else shadowRadius
        jcTextview.setShadowLayer(
            tempShadowRadius.toFloat(), tempShadowDx.toFloat(), tempShadowDy.toFloat(),
            getJcColor(tempShadowColor)
        )
    }

    fun setJcText(txt: String = "") {
        val jcTxt = typedArray.getResourceId(
            R.styleable.jc_textview_attributes_jc_textview_text, 0
        )
        if (!txt.isNullOrEmpty()) jcTextview.text = txt else jcTextview.text =
            resources.getString(jcTxt)
    }

    fun setJcTextColor(color: Int = 0) {
        val jcTextColor = typedArray.getResourceId(
            R.styleable.jc_textview_attributes_jc_textview_textColor,
            0
        )
        val tempColor = if (color > 0) color else jcTextColor
        jcTextview.setTextColor(getJcColor(tempColor))
    }

    fun setJcTextSize(size: Int = 0) {
        val jcTextSize = typedArray.getDimensionPixelSize(
            R.styleable.jc_textview_attributes_jc_textview_textSize,
            0
        )
        val tempSize = if (size > 0) size else jcTextSize
        jcTextview.setTextSize(TypedValue.COMPLEX_UNIT_PX, tempSize.toFloat());
    }

    fun setJcTextPadding(
        jcPadding: Int = 0,
        jcPaddingStart: Int = 0,
        jcPaddingEnd: Int = 0,
        jcPaddingTop: Int = 0,
        jcPaddingBottom: Int = 0
    ) {
        val padding =
            typedArray.getDimensionPixelOffset(
                R.styleable.jc_textview_attributes_jc_textview_padding,
                0
            )

        if (padding > 0f) {
            jcTextview.setPadding(padding, padding, padding, padding)
        }
        val paddingEnd = typedArray.getDimensionPixelOffset(
            R.styleable.jc_textview_attributes_jc_textview_paddingEnd, 0
        )


        val paddingStart = typedArray.getDimensionPixelOffset(
            R.styleable.jc_textview_attributes_jc_textview_paddingStart, 0
        )

        val paddingTop = typedArray.getDimensionPixelOffset(
            R.styleable.jc_textview_attributes_jc_textview_paddingTop, 0
        )

        val paddingBottom = typedArray.getDimensionPixelOffset(
            R.styleable.jc_textview_attributes_jc_textview_paddingBottom, 0
        )

        jcTextview.apply {
            if (paddingEnd > 0) setPaddingEnd(paddingEnd)
            if (paddingTop > 0) setPaddingTop(paddingTop)
            if (paddingBottom > 0) setPaddingBottom(paddingBottom)
            if (paddingStart > 0) setPaddingStart(paddingStart)
        }

        jcTextview.apply {
            if (jcPadding > 0) setPadding(jcPadding, jcPadding, jcPadding, jcPadding)
            if (jcPaddingEnd > 0) setPaddingEnd(jcPaddingEnd)
            if (jcPaddingTop > 0) setPaddingTop(jcPaddingTop)
            if (jcPaddingBottom > 0) setPaddingBottom(jcPaddingBottom)
            if (jcPaddingStart > 0) setPaddingStart(jcPaddingStart)
        }
    }

    fun setJcTextGravity(jcGravity: JcTextGravity? = null) {
        val gravity = values()[typedArray.getInt(
            R.styleable.jc_textview_attributes_jc_textview_gravity,
            0
        )]

        when (jcGravity ?: gravity) {
            START -> jcTextview.gravity = Gravity.START
            END -> jcTextview.gravity = Gravity.END
            TOP -> jcTextview.gravity = Gravity.TOP
            BOTTOM -> jcTextview.gravity = Gravity.BOTTOM
            CENTER -> jcTextview.gravity = Gravity.CENTER
            CENTER_VERTICAL -> jcTextview.gravity = Gravity.CENTER_VERTICAL
            CENTER_HORIZONTAL -> jcTextview.gravity = Gravity.CENTER_HORIZONTAL

        }
    }

    fun setJcTextDirection(direction: JcTextDirections? = null) {
        val txtDirection = JcTextDirections.values()[typedArray.getInt(
            R.styleable.jc_textview_attributes_jc_textview_textDirection,
            0
        )]

        when (direction ?: txtDirection) {
            JcTextDirections.LTR -> {
                jcTextview.textDirection = TextView.TEXT_DIRECTION_LTR
            }
            JcTextDirections.RTL -> {
                jcTextview.textDirection = TextView.TEXT_DIRECTION_RTL
            }
        }

    }

    fun setTxtAlignment(alignment: Int = 0) {
        val textAlignment = typedArray.getResourceId(
            R.styleable.jc_textview_attributes_jc_textview_textAlignment,
            0
        )
        val tempAlignment = if (alignment == 0) textAlignment else alignment
        if (tempAlignment > 0) jcTextview.textAlignment = textAlignment
    }


    fun setSpannableStyle(start: Int, end: Int, style: JcTextSpannableStyle, color: Int) {

        try {
            val spannable = SpannableString(jcTextview.text)
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

            jcTextview.text = spannable
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
            else ->  JcTextSpannableStyle.NORMAL
        }
    }

    fun getJcColor(jcColor: Int) = ContextCompat.getColor(context, jcColor)


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