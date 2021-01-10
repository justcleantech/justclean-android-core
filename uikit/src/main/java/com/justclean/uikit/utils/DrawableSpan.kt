package com.justclean.uikit.utils

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.style.ImageSpan

class DrawableSpan(drawable: Drawable, var paddingStart: Int = 0, var paddingEnd: Int = 0) :
    ImageSpan(drawable) {

    /**
     * Get Drawable dimensions
     * Get the font height
     * Make sure our drawable has height >= font height
     * Adjust font metrics to fit our drawable size
     * @return Int drawable width which is in our case = drawable width + margin from text
     */
    override fun getSize(
        paint: Paint, text: CharSequence, start: Int, end: Int,
        fontMetricsInt: Paint.FontMetricsInt?
    ): Int {
        val drawable = drawable
        val rect = drawable.bounds
        fontMetricsInt?.let {
            val fontMetrics = paint.fontMetricsInt
            val lineHeight = fontMetrics.bottom - fontMetrics.top
            val drHeight = Math.max(lineHeight, rect.bottom - rect.top)
            val centerY = fontMetrics.top + lineHeight / 2
            fontMetricsInt.apply {
                ascent = centerY - drHeight / 2
                descent = centerY + drHeight / 2
                top = ascent
                bottom = descent
            }
        }
        return rect.width() + paddingStart + paddingEnd
    }


    /**
     * Get font height. in our case now it's drawable height
     * Adjust canvas vertically to draw drawable on text vertical center
     * Adjust canvas horizontally to draw drawable with defined margin from text
     */
    override fun draw(
        canvas: Canvas, text: CharSequence, start: Int, end: Int,
        x: Float, top: Int, y: Int, bottom: Int, paint: Paint
    ) {
        val drawable = drawable
        canvas.save()
        val fontMetrics = paint.fontMetricsInt
        val lineHeight = fontMetrics.descent - fontMetrics.ascent + 5
        val centerY = y + fontMetrics.descent - lineHeight / 2
        val transY = centerY - drawable.bounds.height() / 2
        if (paddingStart != 0) {
            canvas.translate(x + paddingStart, transY.toFloat())
        } else {
            canvas.translate(x, transY.toFloat())
        }

        drawable.draw(canvas)
        canvas.restore()
    }

}