package com.justclean.core.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.justclean.core.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import kotlinx.android.synthetic.main.jc_bottom_sheet.view.*
import kotlinx.android.synthetic.main.layout_sheet.view.*
import kotlin.collections.ArrayList


class JCBottomSheet @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    FrameLayout(context, attrs) {

    var items = ArrayList<IItem<*>>()
    val itemAdapter = ItemAdapter<IItem<*>>()
    val fastAdapter = FastAdapter.with(itemAdapter)
    var recyclerView: RecyclerView? = null
    var jcBottomSheetBehavior = BottomSheetBehavior<NestedScrollView>()
    var maxHeight = 500

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_sheet, this, true)
        recyclerView = jcListSheet

        attrs?.let {
            val typedArray =
                context.obtainStyledAttributes(it,
                    R.styleable.jc_sheet_attributes, 0, 0)

            iniSheet()

            initSheetList()

            hideBlackScreen()

            closeSheet()

            iniSheetPeekHeight(typedArray)

            initSheetListMinHeight(typedArray)

            initSheetMaxHeight(typedArray)

            initSheetStyle(typedArray)

            iniDefaultCloseIcon(typedArray)

            initDefaultDashIcon(typedArray)

            initCloseIcon(typedArray)

            initDashIcon(typedArray)

            initSheetTitle(typedArray)

            typedArray.recycle()
        }
    }

    private fun initSheetTitle(typedArray: TypedArray) {
        var withTitle = typedArray.getBoolean(R.styleable.jc_sheet_attributes_jc_with_title, false)
        if (withTitle) {
            txtSheetTitle.visibility = View.VISIBLE
            txtSheetTitle.text =
                typedArray.getString(R.styleable.jc_sheet_attributes_jc_set_sheet_title)
        } else txtSheetTitle.visibility = View.GONE
    }


    private fun iniSheetPeekHeight(typedArray: TypedArray) {
        val sheetPeekHeight =
            typedArray.getDimension(R.styleable.jc_sheet_attributes_jc_behavior_peekHeight, 0f)
        if (sheetPeekHeight > 0) setPeekHeight(sheetPeekHeight.toInt())
    }

    private fun initSheetListMinHeight(typedArray: TypedArray) {
        val sheetListMinHeight =
            typedArray.getDimension(R.styleable.jc_sheet_attributes_jc_sheet_min_height, 0f)
        if (sheetListMinHeight > 0) setMinHeight(sheetListMinHeight.toInt())
    }

    private fun initSheetMaxHeight(typedArray: TypedArray) {
        val sheetMaxHeight =
            typedArray.getDimension(R.styleable.jc_sheet_attributes_jc_sheet_max_height, 0f)
        if (sheetMaxHeight > 0) setMaxSheetHeight(sheetMaxHeight.toInt())
    }

    private fun initSheetStyle(typedArray: TypedArray) {
        val sheetStyle =
            SheetStyle.values()[typedArray.getInt(
                R.styleable.jc_sheet_attributes_jc_sheet_style,
                0
            )]
        setSheetStyle(sheetStyle)
    }

    private fun iniDefaultCloseIcon(typedArray: TypedArray) {
        val withCloseIcon =
            typedArray.getBoolean(R.styleable.jc_sheet_attributes_jc_with_close_icon, false)
        val closeVisibility = if (withCloseIcon) View.VISIBLE else GONE
        withCloseIcon(closeVisibility)
    }

    private fun initDefaultDashIcon(typedArray: TypedArray) {
        val withDashIcon =
            typedArray.getBoolean(R.styleable.jc_sheet_attributes_jc_with_dash_icon, false)
        val dashVisibility = if (withDashIcon) View.VISIBLE else GONE
        withDashIcon(dashVisibility)
    }


    private fun initCloseIcon(typedArray: TypedArray) {
        val closeIcon =
            typedArray.getResourceId(R.styleable.jc_sheet_attributes_jc_close_icon, 0)
        if (closeIcon > 0) imgClose.setImageResource(closeIcon)
    }

    private fun initDashIcon(typedArray: TypedArray) {
        val dashIcon =
            typedArray.getResourceId(R.styleable.jc_sheet_attributes_jc_dash_icon, 0)
        if (dashIcon > 0) viewDash.setBackgroundResource(dashIcon)
    }

    fun initSheetList() {
        jcListSheet.maxHeight = maxHeight
        jcListSheet.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = fastAdapter
        }
        itemAdapter.add(items)
    }

    fun closeSheet() {
        imgClose.setOnClickListener { jcBottomSheetBehavior.state = STATE_COLLAPSED }
    }

    fun iniSheet() {
        jcBottomSheetBehavior = from(jcBottomSheet)
        jcBottomSheetBehavior.state = STATE_EXPANDED
        layoutBlackForSheet.visibility = View.VISIBLE
        jcBottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    STATE_COLLAPSED, STATE_HIDDEN -> {
                        jcBottomSheetBehavior.isHideable = true
                        jcBottomSheetBehavior.state = STATE_COLLAPSED
                        layoutBlackForSheet.visibility = GONE
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                layoutBlackForSheet.visibility = VISIBLE
                layoutBlackForSheet.alpha = slideOffset
            }
        })
    }

    fun withSheetTitle(show: Boolean, title: String) {
        if (show) {
            txtSheetTitle.visibility = View.VISIBLE
            txtSheetTitle.text = title
        }
    }

    fun hideBlackScreen() {
        layoutBlackForSheet.setOnClickListener { jcBottomSheetBehavior.state = STATE_COLLAPSED }
    }

    fun setListAdapter(items: ArrayList<IItem<*>>) {
        this.items = items
        itemAdapter.add(this.items)
    }


    fun setMaxSheetHeight(maxHeight: Int) {
        this.maxHeight = maxHeight
        jcListSheet.maxHeight = maxHeight
    }

    fun setSheetState(state: Int) {
        jcBottomSheetBehavior.state = state
    }

    fun setPeekHeight(height: Int) {
        jcBottomSheetBehavior.peekHeight = height
    }

    fun setMinHeight(sheetListMinHeight: Int) {
        jcListSheet.minimumHeight = sheetListMinHeight
    }

    fun setSheetStyle(style: SheetStyle) {
        when (style) {
            SheetStyle.primary -> {
                jcBottomSheet.setBackgroundResource(R.drawable.bottom_sheet_primary)
            }
            SheetStyle.secondary -> {
                jcBottomSheet.setBackgroundResource(R.drawable.bottom_sheet_secondary)
            }
        }
    }

    fun setEventsHokes(vararg eventHook: ClickEventHook<IItem<*>>) {
        fastAdapter.addEventHooks(eventHook.toList())
    }

    fun withCloseIcon(visibility: Int) {
        imgClose.visibility = visibility
    }

    fun withDashIcon(visibility: Int) {
        viewDash.visibility = visibility
    }

    fun setCloseIcon(res: Int) {
        imgClose.visibility = View.VISIBLE
        imgClose.setImageResource(res)
    }

    fun setDashIcon(res: Int) {
        viewDash.visibility = View.VISIBLE
        viewDash.setBackgroundResource(res)
    }

    fun isCollapsed() = jcBottomSheetBehavior.state == STATE_COLLAPSED

    fun isExpanded() = jcBottomSheetBehavior.state == STATE_EXPANDED

    fun isHidden() = jcBottomSheetBehavior.state == STATE_HIDDEN

    fun isDragging() = jcBottomSheetBehavior.state == STATE_DRAGGING

    enum class SheetStyle {
        primary, secondary
    }
}