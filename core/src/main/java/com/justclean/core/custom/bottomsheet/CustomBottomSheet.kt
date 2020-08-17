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
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.justclean.core.R
import com.justclean.core.heplers.gone
import com.justclean.core.heplers.visible
import kotlinx.android.synthetic.main.custom_bottom_sheet.view.*

/**
 * Custom bottomsheet class to render any view and git rid of configurations boilerplate code
 * @property contentView View? an optional view to be passed if we need to use the sheet layout
 * @property dataSource BottomSheetDataSource configurations values of the sheet
 * @property bottomSheetLayout FrameLayout? the main layout for the fragment serve as transition scene root
 * @property bottomSheetDialog BottomSheetDialog instance of the dialog wrapped to BottomSheetDialogFragment
 * @property sheetStateChanged BottomSheetStateChanged interface to notify with state changing callbacks
 * @property containerLayout LinearLayout the view container inside the sheet
 * @property closeImageView ImageView close indicator will be removed in the future ISA
 * @property titleTextView TextView title to be displayed at the top of the sheet
 * @property dragIndicator View a dash like view to inform the user that the sheet is draggable
 * @constructor accept optional view and mandatory data source to configure the layout
 */
open class CustomBottomSheet(
    private val contentView: View? = null,
    private val dataSource: BottomSheetDataSource
) : BottomSheetDialogFragment() {

    var bottomSheetLayout: FrameLayout? = null
    lateinit var bottomSheetDialog: BottomSheetDialog
    private var sheetStateChanged: BottomSheetStateChanged? = null

    private lateinit var dragIndicator: View
    private lateinit var titleTextView: TextView
    private lateinit var closeImageView: ImageView
    private lateinit var containerLayout: LinearLayout

    /**
     * Setter for the state change callback interface
     * @param sheetStateChanged BottomSheetStateChanged to be registered
     */
    fun setStateChangedCallback(sheetStateChanged: BottomSheetStateChanged) {
        this.sheetStateChanged = sheetStateChanged
    }

    /**
     * Override the method to setup the dialog background, corner radius and dim level
     * Setup the BottomSheetBehavior callbacks
     * @return Dialog as BottomSheetDialog after configuration
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener {
            bottomSheetLayout =
                bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheetLayout?.background = getBackgroundDrawable()
            bottomSheetDialog.window?.setDimAmount(dataSource.dimLevel)
            setupCallbacks(from(bottomSheetLayout!!))
        }
        return bottomSheetDialog
    }

    /**
     * Register callbacks for sheet motions
     * Obtain dim amount by subtract 1 from the slideOffset
     * Control the dim level on slide offset change
     * Report the state change events to the registered callback
     * @param behavior BottomSheetBehavior<FrameLayout> to add callback listeners
     */
    private fun setupCallbacks(behavior: BottomSheetBehavior<FrameLayout>) {
        behavior.addBottomSheetCallback(object :
            BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                val newDim = 1 + slideOffset
                if (dataSource.dimLevel < newDim || slideOffset.isNaN()) return
                bottomSheetDialog.window?.setDimAmount(newDim)
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    STATE_DRAGGING -> sheetStateChanged?.onSheetDragging()
                    STATE_SETTLING -> sheetStateChanged?.onSheetSettling()
                    STATE_EXPANDED -> sheetStateChanged?.onSheetExpanded()
                    STATE_COLLAPSED -> sheetStateChanged?.onSheetCollapsed()
                    STATE_HIDDEN -> sheetStateChanged?.onSheetHidden()
                    STATE_HALF_EXPANDED -> sheetStateChanged?.onSheetHaveExpanded()
                }
            }
        })
    }

    /**
     * Override the create view to provide the custom layout
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.custom_bottom_sheet, container, false)

    /**
     * Override the view created to initiate the header components
     * Add the content view if exists to the container layout
     * Dismiss the dialog if the close icon clicked
     * Bind the header view components from the data source
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        titleTextView = view.sheet_title
        closeImageView = view.close_icon
        dragIndicator = view.drag_indicator
        containerLayout = view.layout_container
        contentView?.let { containerLayout.addView(it) }
        closeImageView.setOnClickListener { dismiss() }
        setupDialogContent()
    }

    /**
     * Set the title if exist or hide the view
     * Control the close icon visibility
     * Control the drag indicator visibility
     */
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

    /**
     * Create drawable by using corner values and background color
     * @return Drawable the background drawable
     */
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