package com.justclean.core

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.jc_button.view.*


class JCButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {

    private var btnPrimaryBackground = 0
    var isButtonEnabled = MutableLiveData<Boolean>()

    init {
        LayoutInflater.from(context).inflate(R.layout.jc_button, this, true)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.jc_attributes, 0, 0)
            val text = resources.getString(
                typedArray
                    .getResourceId(
                        R.styleable.jc_attributes_jc_text,
                        R.string.component_one
                    )
            )
            setJcText(text)
            btnPrimaryBackground = typedArray.getResourceId(
                R.styleable.jc_attributes_jc_background,
                R.drawable.jc_button_background
            )

            val withProgress = typedArray.getBoolean(R.styleable.jc_attributes_jc_progress, false)
            withProgress(withProgress)

            val isPrimary = typedArray.getBoolean(R.styleable.jc_attributes_jc_primary, false)
            if (isPrimary) setJcPrimary()

            val isSecondary = typedArray.getBoolean(R.styleable.jc_attributes_jc_secondary, false)
            if (isSecondary) setJcSecondary()

            val isEnabled = typedArray.getBoolean(R.styleable.jc_attributes_jc_enabled, false)
            layoutJcBtn.setJcEnabled(isEnabled)

            val fontType = typedArray.getResourceId(R.styleable.jc_attributes_jc_font, 0)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                txtJcBtnTitle.typeface = resources.getFont(fontType)
            else
                txtJcBtnTitle.typeface = ResourcesCompat.getFont(context, fontType)



            typedArray.recycle()
        }
    }

    fun withProgress(isProgress: Boolean) {
        if (isProgress) progressJcBtn.visibility = View.VISIBLE
        else progressJcBtn.visibility = View.GONE
    }

    fun setJcPrimary() {
        layoutJcBtn.setBackgroundResource(btnPrimaryBackground)
    }

    fun setJcSecondary() {
        layoutJcBtn.backgroundTintList = context.resources.getColorStateList(R.color.success_green);
    }

    fun setJcText(text: String) {
        txtJcBtnTitle.text = text
    }

    fun setJcBackground(res: Int) {
        layoutJcBtn.setBackgroundResource(res)
    }

    fun isJcEnabled() = layoutJcBtn.isEnabled



    fun setLifeCycleScope(lifecycle: LifecycleOwner) {
        //
    }

}

fun View.setJcEnabled(enable: Boolean) {
    this.isEnabled = enable
    layoutJcBtn.isEnabled = enable
}