package com.app.am_portfolio.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.am_portfolio.ui.tabs.Tab1Fragment
import com.app.am_portfolio.ui.tabs.Tab2Fragment

class MainPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2
    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> Tab1Fragment()
            else -> Tab2Fragment()
        }
}
