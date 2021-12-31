package id.revan.sharingsessionapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.revan.sharingsessionapp.R
import id.revan.sharingsessionapp.data.entities.NewsCategory
import id.revan.sharingsessionapp.presentation.adapter.NewsCategoryAdapter.ViewHolder

class NewsCategoryAdapter(private val newsCategories: List<NewsCategory>) : RecyclerView.Adapter<ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon = itemView.findViewById<ImageView>(R.id.ivIcon)
        val tvCategory = itemView.findViewById<TextView>(R.id.tvCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contentView = LayoutInflater.from(parent.context).inflate(R.layout.item_news_categories, parent, false)
        return ViewHolder(contentView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = newsCategories[position]

        Glide.with(holder.itemView.context).load(item.url).into(holder.ivIcon)
        holder.tvCategory.text = item.category
    }

    override fun getItemCount(): Int = newsCategories.size
}