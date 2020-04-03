package com.chilik1020.mustachepaws.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.models.data.Post
import kotlinx.android.synthetic.main.item_post.view.*

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    private lateinit var items: List<Post>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = if (::items.isInitialized) items.size else 0

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.tvCreatorUsername?.text = items[position].creatorUsername
        holder.tvPostStatus?.text = if(items[position].closed) "Закрыто" else "Открыто"
        holder.tvPostDescription?.text = items[position].description
        holder.tvPostCreatedAt?.text = items[position].createdAt
    }

    fun setData(items: List<Post>) {
        this.items = items
        notifyDataSetChanged()
    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvCreatorUsername: TextView? = null
        var tvPostStatus: TextView? = null
        var tvPostDescription: TextView? = null
        var tvPostCreatedAt: TextView? = null

        init {
            tvCreatorUsername = itemView.creatorUsername
            tvPostStatus = itemView.postStatus
            tvPostDescription = itemView.postDescription
            tvPostCreatedAt = itemView.postCreatedAt
        }
    }
}