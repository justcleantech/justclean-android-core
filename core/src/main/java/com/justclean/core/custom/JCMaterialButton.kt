package com.justclean.core.custom

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

class JCMaterialButton: MaterialButton {

    /**
     * Simple constructor to use when creating a JCMaterialButton from code.
     * @param context The Context the view is running in, through which it can
     * access the current theme, resources, etc.
     **/
    constructor(context: Context) : super(context)

    /**
     * @param context The Context the view is running in, through which it can
     * access the current theme, resources, etc.
     * @param attrs The attributes of the XML Button tag being used to inflate the view.
     */
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    /**
     * @param context The Context the view is running in, through which it can
     * access the current theme, resources, etc.
     * @param attrs The attributes of the XML Button tag being used to inflate the view.
     * @param defStyleAttr The resource identifier of an attribute in the current theme
     * whose value is the the resource id of a style. The specified style’s
     * attribute values serve as default values for the button. Set this parameter
     * to 0 to avoid use of default values.
     */
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}