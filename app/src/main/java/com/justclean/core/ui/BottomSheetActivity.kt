package com.justclean.core.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.paris.R2.id.off
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.justclean.core.R
import com.justclean.core.base.BaseActivity
import com.justclean.core.base.BaseMapActivity
import com.justclean.core.base.LayoutRes
import com.justclean.core.custom.JCBottomSheet
import com.justclean.core.heplers.startActivity
import com.justclean.core.ui.items.SimpleItem
import com.justclean.core.ui.items.SimpleItemTwo
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.listeners.ClickEventHook
import kotlinx.android.synthetic.main.activity_bottom_sheet.*


@LayoutRes(layout = R.layout.activity_bottom_sheet)
class BottomSheetActivity : BaseActivity() {

    override fun onActivityReady(savedInstanceState: Bundle?) {

        var ITEMS = ArrayList<IItem<*>>()
        for (i in 0..2) {
            val item = SimpleItem()
            item.description = "descriptions $i "
            item.name = "Activity  $i "
            ITEMS.add(item)
        }
        for (i in 0..2) {
            val item = SimpleItemTwo()
            ITEMS.add(item)
        }

        jcSheet.setListAdapter(ITEMS)
        jcSheet.setSheetState(BottomSheetBehavior.STATE_EXPANDED)
        jcSheet.setEventsHokes(testClickOne, testClickTwo)
        jcSheet.setMinHeight(400)
        jcSheet.setPeekHeight(300)
        jcSheet.setMaxSheetHeight(800)
        jcSheet.setSheetStyle(JCBottomSheet.SheetStyle.primary)
        jcSheet.withDashIcon(View.VISIBLE)
        jcSheet.withCloseIcon(View.VISIBLE)
        jcSheet.withSheetTitle(true, "JustClean Sheet")


        switch()

    }

    var testClickTwo = (object : ClickEventHook<IItem<*>>() {
        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
            return if (viewHolder is SimpleItem.ViewHolder) {
                viewHolder.description
            } else null
        }

        override fun onClick(
            v: View,
            position: Int,
            fastAdapter: FastAdapter<IItem<*>>,
            item: IItem<*>
        ) {
            startActivity<MapsActivity>()
        }
    })

    var testClickOne = (object : ClickEventHook<IItem<*>>() {
        override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
            return if (viewHolder is SimpleItem.ViewHolder) {
                viewHolder.name
            } else null
        }

        override fun onClick(
            v: View,
            position: Int,
            fastAdapter: FastAdapter<IItem<*>>,
            item: IItem<*>
        ) {
            startActivity<BaseSampleFragment>()
        }
    })

    fun testClick(view: View) {
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
    }


    private fun switch(){
        jcSwitch.setOffText(R.string.off)
        jcSwitch.setOnText(R.string.on)
        jcSwitch.setOnTextColor(R.color.error_red)
    }
}
