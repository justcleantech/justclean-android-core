package com.justclean.core.custom

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.justclean.core.R
import kotlinx.android.synthetic.main.layout_toolbar.view.*


class JCToolBar @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {


    init {
        LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.jc_toolbar_attr, 0, 0)
            val toolbarColor = typedArray.getColor(R.styleable.jc_toolbar_attr_jc_toolbar_color, 0)
            val toolbarIcon = typedArray.getDrawable(R.styleable.jc_toolbar_attr_jc_toolbar_icon)
            val toolbarText =
                typedArray.getResourceId(R.styleable.jc_toolbar_attr_jc_toolbar_text, 0)

            if (toolbarColor > 0)
                layoutToolbar.setBackgroundColor(toolbarColor)
            if (toolbarIcon != null)
                btnBack.setImageDrawable(toolbarIcon)
            if (toolbarText > 0)
                txtScreenTitle.text = context.getText(toolbarText)
        }
    }

    fun setToolbarText(txt: String) {
        txtScreenTitle.text = txt
    }

    fun setToolbarText(txt: Int) {
        txtScreenTitle.text = context.getString(txt)
    }

    fun setToolbarColor(color: Int) {
        layoutToolbar.setBackgroundColor(color)
    }

    fun setToolbarIcon(toolbarIcon: Drawable) {
        btnBack.setImageDrawable(toolbarIcon)
    }

    fun setToolbarIcon(toolbarIcon: Int) {
        btnBack.setImageDrawable(ContextCompat.getDrawable(context,toolbarIcon))
    }

    fun getToolbarText() = txtScreenTitle.text

    fun getToolbarIcon() = btnBack

    fun getToolbarColor() = layoutToolbar.solidColor
}