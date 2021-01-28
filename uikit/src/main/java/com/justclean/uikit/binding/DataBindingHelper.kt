package com.justclean.uikit.binding

import androidx.databinding.BindingAdapter
import com.justclean.uikit.atoms.JCButton

object DataBindingHelper {
    @BindingAdapter("isEnabled")
    fun setEnabledFromBinding(button: JCButton, enabled: Boolean) {
        button.setButtonEnabled(enabled)
    }
}