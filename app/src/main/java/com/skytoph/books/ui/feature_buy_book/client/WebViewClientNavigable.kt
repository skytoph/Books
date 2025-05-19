package com.skytoph.books.ui.feature_buy_book.client

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewClientNavigable(val handler: UpdateCanNavigateBack) : WebViewClient() {

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        handler.update(view?.canGoBack() == true)
    }
}