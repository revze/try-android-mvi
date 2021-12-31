package id.revan.sharingsessionapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.revan.sharingsessionapp.R
import id.revan.sharingsessionapp.presentation.adapter.ShimmerNewsCategoryAdapter.ViewHolder

class ShimmerNewsCategoryAdapter(private val items: List<Any>) : RecyclerView.Adapter<ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contentView = LayoutInflater.from(parent.context).inflate(R.layout.item_shimmer_news_categories, parent, false)
        return ViewHolder(contentView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {}

    override fun getItemCount(): Int = items.size
}