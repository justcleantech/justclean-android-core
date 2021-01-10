package com.justclean.uikit.atoms

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.util.AttributeSet
import com.airbnb.lottie.LottieCompositionFactory
import com.airbnb.lottie.LottieDrawable
import com.google.android.material.button.MaterialButton
import com.justclean.uikit.R
import com.justclean.uikit.utils.DrawableSpan

class JCButton(context: Context, attrs: AttributeSet) :
    MaterialButton(context, attrs) {

    /**
     * Backup the button text to be restored when loading end
     * Convert the progressDrawable to DrawableSpan with applying the padding
     * Create spannableString by appending the loadingString to the button text
     * Assign the progressDrawable callback and start the loading
     * Set the spannableString as the new text for the button
     */
    fun startLoading() {
        val drawableSpan = DrawableSpan(getLottieDrawable())
        val spannableString = SpannableString( "Jimmy").apply {
            setSpan(drawableSpan, length -1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        text = spannableString
    }

    private fun getLottieDrawable(): Drawable {
        val lottieDrawable = LottieDrawable()
        val composition = LottieCompositionFactory.fromRawResSync(context, R.raw.loader).value
        lottieDrawable.composition = composition
        lottieDrawable.repeatCount = Int.MAX_VALUE
        lottieDrawable.playAnimation()
        return lottieDrawable
    }
}