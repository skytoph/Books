package com.skytoph.books.ui.feature_buy_book.component

import android.os.Bundle
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.os.bundleOf
import com.skytoph.books.ui.feature_buy_book.client.UpdateCanNavigateBack
import com.skytoph.books.ui.feature_buy_book.client.WebViewClientNavigable
import com.skytoph.books.ui.feature_buy_book.client.setup

@Composable
fun WebView(
    url: String,
    canNavigateBack: Boolean,
    handler: UpdateCanNavigateBack
) {
    val context = LocalContext.current
    val webView = remember { WebView(context) }
    val bundle: Bundle = rememberSaveable { bundleOf() }

    AndroidView(
        factory = { context ->
            webView.setup(context = context, client = WebViewClientNavigable(handler = handler))
        },
        onRelease = {
            webView.saveState(bundle)
        },
        update = {
            if (bundle.isEmpty)
                webView.loadUrl(url)
            else
                webView.restoreState(bundle)
        }
    )

    BackHandler(enabled = canNavigateBack) {
        webView.goBack()
    }
}