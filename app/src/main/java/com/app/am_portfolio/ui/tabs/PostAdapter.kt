package com.app.am_portfolio.ui.tabs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.app.am_portfolio.data.Post
import com.app.am_portfolio.databinding.ItemPostBinding
import com.google.android.material.chip.Chip
import java.util.Locale

class PostAdapter : RecyclerView.Adapter<PostAdapter.VH>() {

    private val items = mutableListOf<Post>()

    fun submit(newItems: List<Post>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class VH(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) = with(binding) {
            // Title & body
            title.text = item.title
            body.text = item.body

            // Tags → chips
            chipGroup.removeAllViews()
            if (item.tags.isNullOrEmpty()) {
                chipGroup.isVisible = false
            } else {
                chipGroup.isVisible = true
                item.tags.forEach { tag ->
                    chipGroup.addView(createTagChip(tag))
                }
            }

            // Metrics (emoji, no drawable assets needed)
            val likesCount = item.reactions?.likes ?: 0
            val dislikesCount = item.reactions?.dislikes ?: 0
            val viewsCount = item.views ?: 0

            likes.text = "👍 ${formatCount(likesCount)}"
            dislikes.text = "👎 ${formatCount(dislikesCount)}"
            views.text = "👁 ${formatCount(viewsCount)}"
        }

        private fun createTagChip(text: String): Chip {
            return Chip(binding.root.context).apply {
                this.text = text
                isCheckable = false
                isClickable = false
                textSize = 12f
            }
        }

        private fun formatCount(n: Int): String {
            val d = n.toDouble()
            return when {
                d < 1_000 -> n.toString()
                d < 1_000_000 -> String.format(Locale.US, "%.1fk", d / 1_000).trimEnd('0').trimEnd('.')
                else -> String.format(Locale.US, "%.1fM", d / 1_000_000).trimEnd('0').trimEnd('.')
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
    override fun getItemCount(): Int = items.size
}
