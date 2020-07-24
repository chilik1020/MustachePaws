package com.chilik1020.mustachepaws.ui.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import at.blogc.android.views.ExpandableTextView
import com.bumptech.glide.Glide
import com.chilik1020.mustachepaws.R
import com.chilik1020.mustachepaws.models.data.PostVO
import com.chilik1020.mustachepaws.utils.BASE_URL
import com.chilik1020.mustachepaws.utils.LOG_TAG
import kotlinx.android.synthetic.main.item_post.view.*
import kotlinx.android.synthetic.main.item_post.view.etvPostDescription
import javax.inject.Inject

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    private var items: List<PostVO> = listOf()

    @Inject
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.tvCreatorUsername.text = items[position].creatorUsername
        holder.tvPostStatus.text = if(items[position].closed) "[Закрыто]" else "[Актуально]"
        holder.tvPostDescription.text = items[position].description
        holder.tvPostCreatedAt.text = items[position].createdAt

        val link = "${BASE_URL}mustachepaws/posts/image/${items[position].imageLink}"
        Log.d(LOG_TAG, link)
        Glide.with(context).load(link).into(holder.ivPostPhoto)

        Glide.with(context).load(link).circleCrop().into(holder.ivCreatorAvatar)

        holder.tvPostDescription.setInterpolator (OvershootInterpolator())
        holder.tvPostDescription.setOnClickListener {
                if (holder.tvPostDescription.isExpanded)
                    holder.tvPostDescription.collapse()
                else
                    holder.tvPostDescription.expand()
            }
    }

    fun setData(items: List<PostVO>) {
        this.items = items
        notifyDataSetChanged()
    }


    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivPostPhoto: ImageView = itemView.ivPostPhoto
        var ivCreatorAvatar: ImageView = itemView.ivCreatorAvatar
        var tvCreatorUsername: TextView = itemView.creatorUsername
        var tvPostStatus: TextView = itemView.tvPostStatus
        var tvPostDescription: ExpandableTextView = itemView.etvPostDescription
        var tvPostCreatedAt: TextView = itemView.postCreatedAt
    }
}