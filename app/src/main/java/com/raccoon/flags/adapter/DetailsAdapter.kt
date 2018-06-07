package com.raccoon.flags.adapter

import android.support.v7.widget.RecyclerView
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.raccoon.flags.R

class DetailsAdapter : RecyclerView.Adapter<DetailsAdapter.ItemViewHolder>() {

    private var items: SparseArray<String>? = null

    fun updateData(newData: SparseArray<String>) {
        items = newData
        notifyDataSetChanged()
    }

    override fun getItemCount() = items?.size() ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_info, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        items?.let {
            holder.title.setText(it.keyAt(position))
            holder.description.text = it.valueAt(position)
        }
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.text_view_title)
        var description: TextView = view.findViewById(R.id.text_view_description)
    }
}