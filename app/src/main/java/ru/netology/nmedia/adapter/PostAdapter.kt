package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.activity.formatCount
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.dto.Post

typealias OnItemLikeListner = (post: Post) -> Unit
typealias OnItemRepostListner = (post: Post) -> Unit

class PostAdapter(
    private val onItemLikeListner: OnItemLikeListner,
    private val onItemRepostListner: OnItemRepostListner

) : ListAdapter<Post, PostViewHolder>(PostDiffCallBack) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, onItemLikeListner,onItemRepostListner)
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        val post = getItem(position)
        holder.bind(post)
    }


}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onItemLikeListner: OnItemLikeListner,
    private val onItemRepostListner: OnItemRepostListner

) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            autor.text = post.author
            content.text = post.content
            published.text = post.published
            likeCount.text = formatCount(post.likes)
            repostCount.text = formatCount(post.repost)
            if (post.likedByMe) {
                like.setImageResource(R.drawable.ic_liked_24)
            } else {
                like.setImageResource(R.drawable.ic_like_24)
            }

            like.setOnClickListener {
                onItemLikeListner(post)
            }
             repost.setOnClickListener {
             onItemRepostListner(post)
        }
    }
}
}

object PostDiffCallBack: DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(
        oldItem: Post,
        newItem: Post
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Post,
        newItem: Post
    ): Boolean {
       return oldItem == newItem
    }
}