package com.app.am_portfolio.ui.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.am_portfolio.R
import com.app.am_portfolio.data.LinkItem
import com.app.am_portfolio.databinding.FragmentTab1Binding

class Tab1Fragment : Fragment() {
    private var _binding: FragmentTab1Binding? = null
    private val binding get() = _binding!!

    private val adapter = LinkItemAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTab1Binding.inflate(inflater, container, false)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@Tab1Fragment.adapter
            addItemDecoration(
                DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
            )
        }

        // Sample data — replace with your real links/logo as needed
        adapter.submit(
            listOf(
                LinkItem(
                    logoRes = R.drawable.tui,
                    androidUrl = null,
                    iosUrl = "https://apps.apple.com/gb/app/tui-holidays-travel-app/id643831079"
                ),
                LinkItem(
                    logoRes = R.drawable.carblip,
                    androidUrl = null,
                    iosUrl = "https://apps.apple.com/us/app/carblip-your-car-delivered/id1362042544" // iOS link hidden
                ),
                LinkItem(
                    logoRes = R.drawable.aerologix,
                    androidUrl = null,
                    iosUrl = "https://apps.apple.com/au/app/aerologix/id1488307513#?platform=iphone" // Android link hidden
                ),
                LinkItem(
                    logoRes = R.drawable.truckmap,
                    androidUrl = "https://play.google.com/store/apps/details?id=com.truckmap.truckmap",
                    iosUrl = "https://apps.apple.com/us/app/truckmap-truck-gps-routes/id1198422047" // Android link hidden
                ),
                LinkItem(
                    logoRes = R.drawable.symptomate,
                    androidUrl = "https://play.google.com/store/apps/details?id=com.symptomate.mobile&hl=en_IN&gl=US",
                    iosUrl = "https://apps.apple.com/in/app/symptomate-symptom-checker/id837725433"
                ),
                LinkItem(
                    logoRes = R.drawable.moneyclub,
                    androidUrl = "https://play.google.com/store/apps/details?id=com.moneyclub.android&hl=en",
                    iosUrl = null
                ),
                LinkItem(
                    logoRes = R.drawable.youpoll,
                    androidUrl = "https://play.google.com/store/apps/details?id=com.youpollapp.polls.surveys",
                    iosUrl = null
                ),
                LinkItem(
                    logoRes = R.drawable.f2s,
                    androidUrl = "https://play.google.com/store/apps/details?id=com.alen",
                    iosUrl = null
                )
            )
        )

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

