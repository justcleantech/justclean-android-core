package com.justclean.core.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.justclean.core.R

class JCTextView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {

    init {

        attrs.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.jc_textview_attributes,0,0)

//        <attr name="jc_textview_theme" format="reference" />
            val
//        <attr name="jc_textview_background" format="reference" />
//        <attr name="jc_textview_backgroundTint" format="reference" />
//        <attr name="jc_textview_drawableStart" format="reference" />
//        <attr name="jc_textview_drawableEnd" format="reference" />
//        <attr name="jc_textview_elevation" format="dimension" />
//        <attr name="jc_textview_fontFamily" format="reference" />
//        <attr name="jc_textview_maxLines" format="integer" />
//        <attr name="jc_textview_rotationX" format="integer" />
//        <attr name="jc_textview_rotationY" format="integer" />
//        <attr name="jc_textview_shadowColor" format="reference" />
//        <attr name="jc_textview_shadowDx" format="integer" />
//        <attr name="jc_textview_shadowDY" format="integer" />
//        <attr name="jc_textview_shadowRadius" format="integer" />
//        <attr name="jc_textview_text" format="string" />
//        <attr name="jc_textview_textColor" format="reference" />
//        <attr name="jc_textview_textSize" format="dimension" />
//        <attr name="jc_textview_gravity" format="enum">
//            <enum name="center" value="0" />
//            <enum name="start" value="1" />
//            <enum name="end" value="2" />
//        </attr>
//        <attr name="jc_textview_textDirection" format="enum">
//            <enum name="ltr" value="0" />
//            <enum name="rtl" value="1" />
//        </attr>
//        <attr name="jc_textview_textStyle" format="enum">
//            <enum name="italic" value="0" />
//            <enum name="bold" value="1" />
//        </attr>
//        <attr name="jc_textview_textAlignment" format="enum">
//            <enum name="center" value="0" />
//        </attr>
//    </declare-styleable>



        }
    }
}