package com.justclean.core

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.jc_bottom_sheet.view.*
import kotlinx.android.synthetic.main.layout_sheet.view.*
import java.util.ArrayList


class JCBottomSheet @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

    var items =  ArrayList<IItem<*>>()
    val itemAdapter = ItemAdapter<IItem<*>>()
    val fastAdapter = FastAdapter.with(itemAdapter)
    var recyclerView:RecyclerView?=null
    var jcBottomSheetBehavior = BottomSheetBehavior<NestedScrollView>()
    init {
        LayoutInflater.from(context).inflate(R.layout.layout_sheet, this, true)
        recyclerView = jcListSheet

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.jc_sheet_attributes, 0, 0)

            jcListSheet.maxHeight = 600
            jcListSheet.apply {
               layoutManager = LinearLayoutManager(context)
                adapter = fastAdapter
            }

            itemAdapter.add(items)



            jcBottomSheetBehavior = from(jcBottomSheet)
          //  jcBottomSheetBehavior.state = STATE_EXPANDED
            jcBottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                            when(newState){
                                STATE_COLLAPSED , STATE_HIDDEN ->{
                                    jcBottomSheetBehavior.isHideable = true
                                    jcBottomSheetBehavior.peekHeight = 100
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
            typedArray.recycle()
        }
    }

    fun setListAdapter(){
        itemAdapter.add(items)
    }

}