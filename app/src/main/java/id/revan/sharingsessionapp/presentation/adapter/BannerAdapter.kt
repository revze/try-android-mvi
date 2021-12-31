package id.revan.sharingsessionapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.revan.sharingsessionapp.R
import id.revan.sharingsessionapp.data.entity.Banner
import id.revan.sharingsessionapp.presentation.adapter.BannerAdapter.ViewHolder

class BannerAdapter(private val banners: List<Banner>) : RecyclerView.Adapter<ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val ivBanner = itemView.findViewById<ImageView>(R.id.ivBanner)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contentView = LayoutInflater.from(parent.context).inflate(R.layout.item_banners, parent, false)
        return ViewHolder(contentView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = banners[position]

        Glide.with(holder.itemView.context).load(item.url).into(holder.ivBanner)
        holder.tvTitle.text = item.title
    }

    override fun getItemCount(): Int = banners.size
}