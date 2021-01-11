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

    /**
     * Get states array of enabled and disabled
     */
    private val states = arrayOf(
        intArrayOf(android.R.attr.state_enabled),
        intArrayOf(-android.R.attr.state_enabled)
    )

    /**
     * Get colors array of enabled and disabled
     */
    private val colors = intArrayOf(
        Color.parseColor(PURPLE_ENABLED),
        Color.parseColor(PURPLE_DISABLED)
    )

    private val colorStates = ColorStateList(states, colors)

    /**
     * Apply the background tint states colors
     * Fetch loading enabled settings
     * Setup on click listener
     */
    init {
        backgroundTintList = colorStates
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.JCButton, 0, 0)
        loadingEnabled = typedArray.getBoolean(R.styleable.JCButton_loadingEnabled, false)
        typedArray.recycle()
        setOnClickListener(this)
    }

    /**
     * Remove the top and bottom margins
     */
    private fun setInsets() {
        insetTop = 0
        insetBottom = 0
    }

    /**
     * Override button height and width
     * Apply the new insets values
     * Set the dimensions to new desired one
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setInsets()
        val desiredHeight = context.resources.getDimension(R.dimen.buttonHeight).toInt()
        setMeasuredDimension(widthMeasureSpec, desiredHeight)
    }

    /**
     * Override the onclick listener to execute call action onclick
     * Save the user click listener to fire it after play loading
     */
    override fun setOnClickListener(clickListener: OnClickListener?) {
        if (clickListener == this)
            super.setOnClickListener(clickListener)
        else
            userOnClickListener = clickListener
    }

    /**
     * Start loading if needed
     * Fire user click listener
     */
    override fun onClick(v: View?) {
        if (loadingEnabled) {
            startLoading()
            isEnabled = false
        }
        userOnClickListener?.onClick(v)
    }

    /**
     * Tur on loading indicator
     * Save the original text
     * Change text to the lottie loader
     */
    private fun startLoading() {
        isLoading = true
        originalText = text.toString()
        text = getSpannableString()
    }

    /**
     * Get lottie loader as string using the lottie drawable
     * SpannableString is applying the loader fot the last index in the text
     * Placeholder character is used to be replaced with the loader
     * Register the drawable callback to invalidate drawable to play animation
     * Play the lottie loading animation
     */
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

    /**
     * Load lottie loading json inside drawable
     * Adjust the width and height to best fit in the design
     * Make repeat count endless to run infinite loop
     */
    private fun getLottieDrawable(): LottieDrawable {
        val lottieDrawable = LottieDrawable()
        lottieDrawable.composition = LottieCompositionFactory.fromRawResSync(context, R.raw.loader).value
        lottieDrawable.setBounds(0, 0,
            (lottieDrawable.intrinsicWidth / 4),
            (lottieDrawable.intrinsicHeight / 4)
        )
        lottieDrawable.repeatCount = Int.MAX_VALUE
        return lottieDrawable
    }

    /**
     * Register drawable callback to notify the button
     * when the drawable is drawn and need to be invalidated
     */
    private val callback = object : Drawable.Callback {
        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {}
        override fun unscheduleDrawable(who: Drawable, what: Runnable) {}
        override fun invalidateDrawable(who: Drawable) {
            this@JCButton.invalidate()
        }
    }

    /**
     * Set the end text if exist
     * Apply the original one if not exist
     * Enable the button again
     * Change loading indicator to off
     */
    fun reset(endText: String? = null) {
        text = endText ?: originalText
        isEnabled = true
        isLoading = false
    }

    /**
     * Indicate button loading status
     */
    fun isButtonLoading() = isLoading

    companion object {
        const val PLACEHOLDER = "~"
        const val PURPLE_ENABLED = "#641bb4"
        const val PURPLE_DISABLED = "#c1a4e1"
    }
}