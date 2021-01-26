package com.justclean.uikit.models

import androidx.databinding.ObservableField

data class ValidationField(
    val field: ObservableField<String>,
    var valid: Boolean = false
)