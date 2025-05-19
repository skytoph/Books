package com.skytoph.books.ui.feature_buy_book.client

import android.content.Context
import android.view.ViewGroup
import android.webkit.WebView
import com.skytoph.books.R

fun WebView.setup(context: Context, client: WebViewClientNavigable): WebView = this.apply {
    layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )
    webViewClient = client
    settings.javaScriptEnabled = true
    settings.safeBrowsingEnabled = true
    settings.userAgentString = context.getString(R.string.user_agent)
    settings.domStorageEnabled = true
}