package com.mdtech.imgurgram.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.Coil
import coil.request.ImageRequest
import com.mdtech.imgurgram.R
import com.mdtech.imgurgram.databinding.FragmentFeedBinding
import com.mdtech.libimgur.models.Image

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedFragment : Fragment() {

    companion object {
        fun newInstance() = FeedFragment()
    }

    private val viewModel: FeedViewModel by viewModels()
    private val feedAdapter = FeedRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val feed = arguments?.getString("feed")  //TODO: turn into enum
        feed?.let { viewModel.updateFeed(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)
        binding.rvGalleryFeed.layoutManager = LinearLayoutManager(requireContext())
        binding.rvGalleryFeed.adapter = feedAdapter

    viewModel.feed.observe(viewLifecycleOwner) {
        it.forEach{ image ->
            val request = ImageRequest.Builder(requireContext())
                .data("\"https://i.imgur.com/${image.cover}.jpg")
                .placeholder(R.drawable.placeholder)
//                .size(binding.rvGalleryFeed.width)
                .build()
            Coil.imageLoader(requireContext()).enqueue(request)
        }
        feedAdapter.submitList(it)
    }
        return binding.root
    }
}