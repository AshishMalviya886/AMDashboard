package com.app.am_portfolio.ui.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.am_portfolio.data.ApiClient
import com.app.am_portfolio.data.Post
import com.app.am_portfolio.databinding.FragmentTab2Binding
import kotlinx.coroutines.launch

class Tab2Fragment : Fragment() {
    private var _binding: FragmentTab2Binding? = null
    private val binding get() = _binding!!

    private val adapter = PostAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTab2Binding.inflate(inflater, container, false)

        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
        binding.recycler.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        fetchData()
        return binding.root
    }

    private fun fetchData() {
        binding.progress.visibility = View.VISIBLE
        lifecycleScope.launch {
            try {
                val resp = ApiClient.api.getPosts() // PostsResponse
                adapter.submit(resp.posts)
            } catch (e: Exception) {
                // Show a single error row
                adapter.submit(
                    listOf(
                        Post(
                            id = -1,
                            title = "Error loading posts",
                            body = e.localizedMessage ?: "Unknown error"
                        )
                    )
                )
            } finally {
                binding.progress.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
