package com.justclean.core.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.justclean.core.R
import kotlinx.android.synthetic.main.jc_toggel_button.view.*


class JCSwitchButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {
    private val constraintSet = ConstraintSet()
    var textOn = 0
    var textOff = 0
    var textOnColor = 0
    var textOffColor = 0
    var trackOnDrawable = 0
    var trackOffDrawable = 0
    var thumbDrawable = 0
    var jcMinWidth = 0

    init {
        LayoutInflater.from(context).inflate(R.layout.jc_toggel_button, this, true)
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.jc_switch_attributes, 0, 0)

        textOn = typedArray.getResourceId(R.styleable.jc_switch_attributes_jc_text_on, 0)
        textOff = typedArray.getResourceId(R.styleable.jc_switch_attributes_jc_text_off, 0)
        textOnColor = typedArray.getResourceId(R.styleable.jc_switch_attributes_jc_text_on_color, 0)
        textOffColor =
            typedArray.getResourceId(R.styleable.jc_switch_attributes_jc_text_off_color, 0)
        trackOnDrawable =
            typedArray.getResourceId(R.styleable.jc_switch_attributes_jc_track_on_drawable, 0)
        trackOffDrawable =
            typedArray.getResourceId(R.styleable.jc_switch_attributes_jc_track_off_drawable, 0)
        thumbDrawable =
            typedArray.getResourceId(R.styleable.jc_switch_attributes_jc_thumb_drawable, 0)
        jcMinWidth = typedArray.getInteger(R.styleable.jc_switch_attributes_jc_switchMinWidth, 0)


        setSwitch()
        setOffState()
        cSwitch.setOnCheckedChangeListener { _, isChecked ->


            if (isChecked) {
                cSwitchText.text = context.getString(textOn)
                cSwitchText.setTextColor(ContextCompat.getColor(context, textOnColor))
                constraintSet.connect(
                    R.id.cSwitchText,
                    ConstraintSet.LEFT,
                    R.id.cSwitch,
                    ConstraintSet.LEFT,
                    0
                )
                constraintSet.connect(
                    R.id.cSwitchText,
                    ConstraintSet.RIGHT,
                    ConstraintSet.UNSET,
                    ConstraintSet.RIGHT,
                    0
                )
                cSwitch.trackDrawable = ContextCompat.getDrawable(context, trackOnDrawable)
            } else {
                setOffState()
            }
            constraintSet.applyTo(layoutSwitch)
        }
    }

    private fun setSwitch() {
        constraintSet.clone(layoutSwitch)
        constraintSet.connect(
            R.id.cSwitchText,
            ConstraintSet.TOP,
            R.id.cSwitch,
            ConstraintSet.TOP,
            0
        )
        constraintSet.connect(
            R.id.cSwitchText,
            ConstraintSet.BOTTOM,
            R.id.cSwitch,
            ConstraintSet.BOTTOM,
            0
        )
        cSwitch.thumbDrawable = ContextCompat.getDrawable(context, R.drawable.custom_thumb)
        constraintSet.applyTo(layoutSwitch)
    }

    private fun setOffState() {
        cSwitchText.text = context.getString(textOff)
        cSwitchText.setTextColor(ContextCompat.getColor(context, textOffColor))
        constraintSet.connect(
            R.id.cSwitchText,
            ConstraintSet.RIGHT,
            R.id.cSwitch,
            ConstraintSet.RIGHT,
            0
        )
        constraintSet.connect(
            R.id.cSwitchText,
            ConstraintSet.LEFT,
            ConstraintSet.UNSET,
            ConstraintSet.LEFT,
            0
        )
        cSwitch.trackDrawable = ContextCompat.getDrawable(context, trackOffDrawable)
        cSwitch.thumbDrawable = ContextCompat.getDrawable(context, thumbDrawable)
        constraintSet.applyTo(layoutSwitch)
    }


    fun setOffText(txt: Int) {
        textOff = txt
    }

    fun setOnText(txt: Int) {
        textOn = txt
    }

    fun setOnTextColor(color: Int) {
        textOnColor = color
    }

    fun setOffTextColor(color: Int) {
        textOffColor = color
    }

    fun setOffTrackDrawable(drawable: Int) {
        trackOffDrawable = drawable
    }

    fun setOnTrackDrawable(drawable: Int) {
        trackOnDrawable = drawable
    }

    fun setOnThumbDrawable(drawable: Int) {
        thumbDrawable = drawable
    }
}