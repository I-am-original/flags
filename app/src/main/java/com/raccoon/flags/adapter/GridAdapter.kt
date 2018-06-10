package com.raccoon.flags.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.raccoon.flags.R
import com.raccoon.flags.api.DataModel
import com.squareup.picasso.Picasso

/**
 * Adapter for RecyclerView in main GridActivity
 * Represents data as cardViews with flag and name
 */
class GridAdapter(private val clickListener: ItemClickListener) : RecyclerView.Adapter<GridAdapter.ItemViewHolder>() {

    private val countriesList: MutableList<DataModel> = ArrayList()

    fun updateList(newList: List<DataModel>) {
        countriesList.clear()
        countriesList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = countriesList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val countryData = countriesList[position]
        holder.title.text = countryData.name
        Picasso.get().load(countryData.getFlagUri()).into(holder.image)

        holder.itemView.setOnClickListener { clickListener.onItemClicked(holder.adapterPosition, countryData, holder.itemView) }
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.image_flag)
        var title: TextView = view.findViewById(R.id.title_name)
    }
}

interface ItemClickListener {

    fun onItemClicked(position: Int, data: DataModel, sharedElement: View)

}
