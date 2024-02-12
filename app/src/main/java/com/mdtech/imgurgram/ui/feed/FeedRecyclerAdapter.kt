package com.mdtech.imgurgram.ui.feed

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mdtech.imgurgram.R
import com.mdtech.imgurgram.databinding.ListItemGalleryImageBinding
import com.mdtech.libimgur.models.Image

class FeedRecyclerAdapter() :
    ListAdapter<Image, FeedRecyclerAdapter.FeedViewHolder>(FeedDiffCallBack()) {

    class FeedViewHolder(val binding: ListItemGalleryImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val captionTextView = binding.captionTextView
        val imageView = binding.imageView
    }

    private class FeedDiffCallBack : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            //return oldItem.toString().equals(newItem.toString())
            return oldItem.toString() == newItem.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater: LayoutInflater = parent.context.getSystemService(LayoutInflater::class.java)
        val binding = ListItemGalleryImageBinding.inflate(inflater, parent, false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
       val image = getItem(position)
        holder.binding.captionTextView.text= image.title
        holder.binding.imageView.load("https://i.imgur.com/${image.cover}.jpg"){
            placeholder(R.drawable.placeholder)
            error(R.drawable.error_placeholder)
        }

    }
}