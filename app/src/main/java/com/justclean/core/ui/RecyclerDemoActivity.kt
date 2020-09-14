package com.justclean.core.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.justclean.core.R
import com.justclean.core.heplers.addSwipeItemAction
import kotlinx.android.synthetic.main.activity_recycler_demo.*

class RecyclerDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_demo)

        val list = mutableListOf("0", "1", "2", "3", "4", "5", "6", "7", "9", "10")
        val deleteIcon = ResourcesCompat.getDrawable(resources, R.drawable.ic_close_normal, null)
        deleteIcon?.setTint(Color.WHITE)
        val adapter = RecyclerAdapter(list)

        recyclerView.addSwipeItemAction(deleteIcon!!, Color.RED) {
            list.removeAt(it.adapterPosition)
            adapter.notifyItemRemoved(it.adapterPosition)
        }
        recyclerView.adapter = adapter
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