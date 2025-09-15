package com.app.am_portfolio.data

import androidx.annotation.DrawableRes

data class LinkItem(
    @DrawableRes val logoRes: Int? = null,
    val androidUrl: String? = null, // e.g. "https://play.google.com/store/apps/details?id=com.example"
    val iosUrl: String? = null      // e.g. "https://apps.apple.com/app/id1234567890"
)
