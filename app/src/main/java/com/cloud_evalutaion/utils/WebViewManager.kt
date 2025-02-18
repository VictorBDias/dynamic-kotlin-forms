package com.cloud_evalutaion.utils

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView

class WebViewManager(context: Context) {
    val webView: WebView = WebView(context).apply {
        settings.domStorageEnabled = true
//        settings.loadWithOverviewMode = true
//        settings.useWideViewPort = true
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    fun loadContent(html: String) {
        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
    }

    fun cleanUp() {
        webView.stopLoading()
        webView.clearHistory()
        webView.clearCache(true)
        webView.loadUrl("about:blank")
        webView.onPause()
        webView.removeAllViews()
        (webView.parent as? ViewGroup)?.removeView(webView)
        webView.destroy()
    }
}
