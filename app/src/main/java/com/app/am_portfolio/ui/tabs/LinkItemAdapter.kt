package com.app.am_portfolio.ui.tabs


import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.app.am_portfolio.data.LinkItem
import com.app.am_portfolio.databinding.LinkItemBinding

class LinkItemAdapter(
    private val items: MutableList<LinkItem> = mutableListOf()
) : RecyclerView.Adapter<LinkItemAdapter.VH>() {

    fun submit(newItems: List<LinkItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class VH(private val binding: LinkItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LinkItem) = with(binding) {
            // Logo (optional)
            if (item.logoRes != null && item.logoRes != 0) {
                logo.setImageResource(item.logoRes)
                logo.isVisible = true
            } else {
                logo.isVisible = false
            }

            // Links (optional)
            androidLink.isVisible = !item.androidUrl.isNullOrBlank()
            iosLink.isVisible = !item.iosUrl.isNullOrBlank()

            androidLink.setOnClickListener {
                item.androidUrl?.let { openUrl(it) }
            }
            iosLink.setOnClickListener {
                item.iosUrl?.let { openUrl(it) }
            }
        }

        private fun openUrl(url: String) {
            val ctx = binding.root.context
            val uri = when {
                url.startsWith("package:") -> {
                    // Shorthand support: "package:com.example.app" → Play Store deep link
                    val pkg = url.removePrefix("package:")
                    Uri.parse("market://details?id=$pkg")
                }
                else -> Uri.parse(url)
            }
            val intent = Intent(Intent.ACTION_VIEW, uri)
            ctx.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = LinkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
    override fun getItemCount(): Int = items.size
}
