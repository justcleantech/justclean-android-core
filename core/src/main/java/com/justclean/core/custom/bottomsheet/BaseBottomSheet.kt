package com.justclean.core.custom.bottomsheet

import android.app.Dialog
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.justclean.core.R
import com.justclean.core.heplers.gone
import com.justclean.core.heplers.visible

open class BaseBottomSheet(
    private val contentView: View? = null,
    private val dataSource: BottomSheetDataSource
) : BottomSheetDialogFragment() {

    var bottomSheetLayout: FrameLayout? = null
    lateinit var bottomSheetDialog: BottomSheetDialog

    private lateinit var sheetLayout: LinearLayout
    private lateinit var closeImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var dragIndicator: View

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            bottomSheetLayout =
                bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheetLayout?.background = getBackgroundDrawable()
            bottomSheetDialog.window?.setDimAmount(dataSource.dimLevel)
            setupCallbacks(BottomSheetBehavior.from(bottomSheetLayout!!))
        }
        return bottomSheetDialog
    }

    private fun setupCallbacks(behavior: BottomSheetBehavior<FrameLayout>) {
        behavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            override fun onStateChanged(bottomSheet: View, newState: Int) {}
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.base_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titleTextView = view.findViewById(R.id.sheet_title)
        closeImageView = view.findViewById(R.id.close_icon)
        dragIndicator = view.findViewById(R.id.drag_indicator)
        sheetLayout = view.findViewById(R.id.layout_container)
        contentView?.let { sheetLayout.addView(it) }
        setupDialogContent()
    }

    private fun setupDialogContent() {

        if (dataSource.title != null)
            titleTextView.text = dataSource.title
        else
            titleTextView.gone()

        if (dataSource.isCloseVisible)
            closeImageView.setImageResource(dataSource.closeIcon)
        else
            closeImageView.gone()

        if (dataSource.isDragVisible)
            dragIndicator.visible()
        else
            dragIndicator.gone()
    }

    private fun getBackgroundDrawable(): Drawable {
        val shape = ShapeDrawable(
            RoundRectShape(
                floatArrayOf(
                    dataSource.topLeftCorner, dataSource.topLeftCorner,
                    dataSource.topRightCorner, dataSource.topRightCorner,
                    dataSource.bottomRightCorner, dataSource.bottomRightCorner,
                    dataSource.bottomLeftCorner, dataSource.bottomLeftCorner
                ),
                null, null
            )
        )
        shape.paint.color = dataSource.backgroundColor
        return shape
    }

}