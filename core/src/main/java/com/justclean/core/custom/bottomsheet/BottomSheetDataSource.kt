package com.justclean.core.custom.bottomsheet

import android.graphics.Color
import com.justclean.core.R

/**
 * Data source to hold all the configurations properties
 * @property title String? title for sheet header
 * @property dimLevel Float dim amount for overlay the app content
 * @property topLeftCorner Float top left corner radius value
 * @property topRightCorner Float top right corner radius value
 * @property bottomLeftCorner Float bottom left corner radius value
 * @property bottomRightCorner Float bottom right corner radius value
 * @property isDragVisible Boolean show or hide the drag indicator
 * @property isCloseVisible Boolean show or hide the close icon
 * @property backgroundColor Int sheet layout background color
 * @property closeIcon Int resource image for the close ImageView
 * @constructor all fields is optional to be passed from here
 */
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