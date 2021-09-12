package com.example.lcontroller
import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.view.Window
import android.webkit.DownloadListener
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import java.lang.Exception



class LcActivity : Activity() {
    var mWebView :WebView? =null
    var mUrl:String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_lcactivity)
        mWebView =   findViewById<WebView>(R.id.webView)

        mUrl = intent.getStringExtra("url").toString()
        if (mUrl != null && !mUrl.endsWith(".apk")) {
            goToWeb(mUrl)
        } else {
            if (!TextUtils.isEmpty(mUrl)) {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.data = Uri.parse(mUrl)
                startActivity(intent)
            }
        }
    }


    private fun goToWeb(url: String) {
        val webSettings: WebSettings = mWebView!!.getSettings()
//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.javaScriptEnabled = true


//设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。
        webSettings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.displayZoomControls = false //隐藏原生的缩放控件

//其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK //关闭webview中缓存
        webSettings.allowFileAccess = true //设置可以访问文件
        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = true //支持自动加载图片
        webSettings.defaultTextEncodingName = "utf-8" //设置编码格式
        webSettings.domStorageEnabled = true
        //步骤3. 复写shouldOverrideUrlLoading()方法，使得打开网页时不调用系统浏览器， 而是在本WebView中显示
        mWebView!!.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                try {
                    Log.e("Loading===", url)
                    if (url.startsWith("http:") || url.startsWith("https:")) {
                        view.loadUrl(url)
                    } else {
//                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                        startActivity(intent);
                    }
                } catch (e: Exception) {
                    return false
                }
                return true
            }
        })
        mWebView!!.setDownloadListener(DownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            Log.e("Download===", url)
            //                initUpDate(url, "lastVersion.apk");
            val intent = Intent()
            intent.action = Intent.ACTION_VIEW
            intent.data = Uri.parse(url)
            startActivity(intent)
        })
        mWebView!!.loadUrl(url)
    }
}
