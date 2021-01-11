package com.justclean.core.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.button.MaterialButton
import com.justclean.core.R
import com.justclean.core.heplers.DrawableSpan

/**
 * Subclass of MaterialButton adding the loading feature with start() and end() methods
 * @property loaderPadding Int to be fetched from xml if custom value passed default is 50
 * @property loaderColor Int to be fetched from xml if custom color passed default is currentTextColor
 * @property buttonText String backup of original text ot be used after loading end
 * @property callback to invalidate the progress drawable before using it
 * @constructor fetch the styled attributes if exist or use the defaults
 */
class JCMaterialButton(context: Context, attrs: AttributeSet) :
    MaterialButton(context, attrs) {

    private var loaderPadding: Int
    private var loaderColor: Int
    private var buttonText: String

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.JCMaterialButton, 0, 0)
        loaderColor = typedArray.getColor(R.styleable.JCMaterialButton_loaderColor, currentTextColor)
        loaderPadding = typedArray.getInteger(R.styleable.JCMaterialButton_loaderPadding, 50)
        buttonText = text.toString()
        typedArray.recycle()
    }

    /**
     * Backup the button text to be restored when loading end
     * Convert the progressDrawable to DrawableSpan with applying the padding
     * Create spannableString by appending the loadingString to the button text
     * Assign the progressDrawable callback and start the loading
     * Set the spannableString as the new text for the button
     * @param loadingString String?
     */
    fun startLoading(loadingString: String? = null) {
        buttonText = text.toString()
        val progressDrawable = getProgressDrawable()
        val drawableSpan = DrawableSpan(progressDrawable, loaderPadding)
        val spannableString = SpannableString(loadingString ?: "$text ").apply {
            setSpan(drawableSpan, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        progressDrawable.callback = callback
        progressDrawable.start()
        text = spannableString
    }

    /**
     * Create progress drawable with large style
     * Change the drawable tint color
     * Define bounds to show drawable correctly
     * @return CircularProgressDrawable customized instance
     */
    private fun getProgressDrawable() = CircularProgressDrawable(context).apply {
        setStyle(CircularProgressDrawable.LARGE)
        setColorSchemeColors(loaderColor)
        val size = (centerRadius + strokeWidth).toInt() * 2
        setBounds(0, 0, size, size)
    }

    /**
     * Register callbacks to invalidate the button view
     * for redrawing the loader correctly before using it
     */
    private val callback = object : Drawable.Callback {
        override fun unscheduleDrawable(who: Drawable, what: Runnable) {}
        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {}
        override fun invalidateDrawable(who: Drawable) {
            this@JCMaterialButton.invalidate()
        }
    }

    /**
     * Change the button text with the old text to
     * remove the loader if no custom text passed
     * @param endString String? custom text to be displayed
     */
    fun endLoading(endString: String? = null) {
        text = endString ?: buttonText
    }
}