package com.justclean.uikit.atoms

import android.content.Context
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

    private var userOnClickListener: OnClickListener? = null

    init {
        setOnClickListener(this)
    }

    override fun setOnClickListener(clickListener: OnClickListener?) {
        if (clickListener == this)
            super.setOnClickListener(clickListener)
        else
            userOnClickListener = clickListener
    }

    override fun onClick(v: View?) {
        startLoading()
        userOnClickListener?.onClick(v)
    }

    private fun startLoading() {
        val lottieDrawable = getLottieDrawable()
        val drawableSpan = DrawableSpan(lottieDrawable)
        val spannableString = SpannableString(PLACEHOLDER).apply {
            setSpan(drawableSpan, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        lottieDrawable.callback = callback
        lottieDrawable.playAnimation()
        text = spannableString
    }

    private fun getLottieDrawable(): LottieDrawable {
        val lottieDrawable = LottieDrawable()
        lottieDrawable.composition = LottieCompositionFactory.fromRawResSync(context, R.raw.loader).value
        lottieDrawable.setBounds(0, 0,
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

    companion object {
        const val PLACEHOLDER = "~"
    }
}