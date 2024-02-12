package com.mdtech.imgurgram.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.mdtech.imgurgram.R
import com.mdtech.imgurgram.databinding.ListItemStoryBinding
import com.mdtech.imgurgram.ui.story.StoryActivity
import com.mdtech.libimgur.models.Tag

class StoriesRecyclerAdapter :
    ListAdapter<Tag, StoriesRecyclerAdapter.StoriesViewHolder>(StoriesDiffCallback()) {

    class StoriesViewHolder(val binding: ListItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    private class StoriesDiffCallback : DiffUtil.ItemCallback<Tag>() {
        override fun areItemsTheSame(oldItem: Tag, newItem: Tag) = (oldItem == newItem)

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Tag, newItem: Tag) = (oldItem === newItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val inflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = ListItemStoryBinding.inflate(inflater, parent, false)
        return StoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val tag = getItem(position)
        holder.binding.tvStory.text = tag.displayName
        holder.binding.ivStoryHead.load("https://i.imgur.com/${tag.backgroundHash}.jpg") {
            placeholder(R.drawable.placeholder)
            error(R.drawable.error_placeholder)
            scale(Scale.FILL)
        }
        holder.binding.root.apply {
            setOnClickListener {
                context.startActivity(
                    Intent(context, StoryActivity::class.java).apply {
                        putExtra("tag",tag.name)
                    }
                )
            }
        }
    }
}