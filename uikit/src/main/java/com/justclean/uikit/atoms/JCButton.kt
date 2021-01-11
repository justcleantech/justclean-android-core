package com.justclean.uikit.atoms

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.util.AttributeSet
import android.view.View
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.button.MaterialButton
import com.justclean.uikit.R
import com.justclean.uikit.utils.DrawableSpan

class JCButton(context: Context, attrs: AttributeSet) :
    MaterialButton(context, attrs), View.OnClickListener {

    private var loadingEnabled = false
    private var isLoading = false
    private var originalText = ""
    private var userOnClickListener: OnClickListener? = null

    private val states = arrayOf(
        intArrayOf(android.R.attr.state_enabled), // enabled
        intArrayOf(-android.R.attr.state_enabled) // disabled
    )

    private val colors = intArrayOf(
        Color.parseColor("#641bb4"), // enabled color
        Color.parseColor("#c1a4e1") // disabled color
    )

    private val colorStates = ColorStateList(states, colors)

    init {
        backgroundTintList = colorStates
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.JCButton, 0, 0)
        loadingEnabled = typedArray.getBoolean(R.styleable.JCButton_loadingEnabled, false)
        typedArray.recycle()
        setOnClickListener(this)
    }

    private fun setInsets() {
        insetTop = 0
        insetBottom = 0
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setInsets()
        val desiredHeight = context.resources.getDimension(R.dimen.buttonHeight).toInt()
        setMeasuredDimension(widthMeasureSpec, desiredHeight)
    }

    override fun setOnClickListener(clickListener: OnClickListener?) {
        if (clickListener == this)
            super.setOnClickListener(clickListener)
        else
            userOnClickListener = clickListener
    }

    override fun onClick(v: View?) {
        if (loadingEnabled) {
            startLoading()
            isEnabled = false
        }
        userOnClickListener?.onClick(v)
    }

    private fun startLoading() {
        isLoading = true
        originalText = text.toString()
        text = getSpannableString()
    }

    private fun getSpannableString(): SpannableString {
        val lottieDrawable = getLottieDrawable()
        val drawableSpan = DrawableSpan(lottieDrawable)
        val spannableString = SpannableString(PLACEHOLDER).apply {
            setSpan(drawableSpan, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        lottieDrawable.callback = callback
        lottieDrawable.playAnimation()
        return spannableString
    }

    private fun getLottieDrawable(): LottieDrawable {
        val lottieDrawable = LottieDrawable()
        lottieDrawable.composition =
            LottieCompositionFactory.fromRawResSync(context, R.raw.loader).value
        lottieDrawable.setBounds(
            0, 0,
            (lottieDrawable.intrinsicWidth / 3.3).toInt(),
            (lottieDrawable.intrinsicHeight / 3.3).toInt()
        )
        lottieDrawable.repeatCount = Int.MAX_VALUE
        return lottieDrawable
    }

    private val callback = object : Drawable.Callback {
        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {}
        override fun unscheduleDrawable(who: Drawable, what: Runnable) {}
        override fun invalidateDrawable(who: Drawable) {
            this@JCButton.invalidate()
        }
    }

    fun reset() {
        text = originalText
        isEnabled = true
        isLoading = false
    }

    fun isButtonLoading() = isLoading

    companion object {
        const val PLACEHOLDER = "~"
    }
}