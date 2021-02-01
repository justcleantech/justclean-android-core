package com.justclean.sample.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.justclean.core.R
import com.justclean.core.heplers.addSwipeItemAction
import kotlinx.android.synthetic.main.activity_recycler_demo.*

class RecyclerDemoActivity : AppCompatActivity() {

    var removedItemIndex = -1
    var removedItemValue = ""

    private val list = mutableListOf("0", "1", "2", "3", "4", "5", "6", "7", "9", "10")
    private val adapter = RecyclerAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_demo)

        val deleteIcon = ResourcesCompat.getDrawable(resources, R.drawable.ic_close_normal, null)
        deleteIcon?.setTint(Color.WHITE)

        recyclerView.addSwipeItemAction(deleteIcon!!, Color.RED) {
            removedItemIndex = it.adapterPosition
            removedItemValue = list[removedItemIndex]
            list.removeAt(removedItemIndex)
            adapter.notifyItemRemoved(removedItemIndex)
            showSnackBar()
        }
        recyclerView.adapter = adapter
    }

    private fun showSnackBar() {
        val snack = Snackbar.make(recyclerView, "Undo delete item", Snackbar.LENGTH_INDEFINITE)
        snack.setAction("Undo") {
            list.add(removedItemIndex, removedItemValue)
            adapter.notifyItemInserted(removedItemIndex)
        }
        snack.show()
    }

}

class RecyclerAdapter(private val myDataset: List<String>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_text_view, parent, false) as TextView
        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size
}