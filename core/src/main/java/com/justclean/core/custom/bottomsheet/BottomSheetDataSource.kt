package com.justclean.core.custom.bottomsheet

import android.graphics.Color
import com.justclean.core.R

data class BottomSheetDataSource(
    val title: String? = null,
    val dimLevel: Float = 0.5f,
    val topLeftCorner: Float = 50f,
    val topRightCorner: Float = 50f,
    val bottomLeftCorner: Float = 0f,
    val bottomRightCorner: Float = 0f,
    val isDragVisible: Boolean = false,
    val isCloseVisible: Boolean = false,
    val backgroundColor: Int = Color.WHITE,
    val closeIcon: Int = R.drawable.ic_close_normal
)