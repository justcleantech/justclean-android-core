package com.justclean.samples

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.justclean.core.R
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import kotlinx.android.synthetic.main.activity_bottom_sheet.*

class BottomSheetActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_sheet)


        var ITEMS = ArrayList<IItem<*>>()
        for (i in 0..10) {
            val item = SimpleItem()
            item.description = "descriptions $i "
            item.name = "name $i "
            ITEMS.add(item)
        }

        jcSheet.items = ITEMS
        jcSheet.setListAdapter()


        Toast.makeText(this, "-- ${ITEMS.size}", Toast.LENGTH_SHORT).show()
    }

    fun testClick(view: View) {
        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
    }
}
