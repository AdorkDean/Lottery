package com.jz.lottery.activity;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.DownloadListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;
import com.jz.lottery.base.BaseActivity;


public class ShowUrlActivity extends BaseActivity {
    private WebView mWebView;
    private String url;
    WebSettings webSettings;

    class MyWebChromeClient extends WebChromeClient {

        /* renamed from: com.wwp.www.vrcpchaxun.activity.ShowUrlActivity.MyWebChromeClient.1 */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ String val$message;

            AnonymousClass1(String str) {
                this.val$message = str;
            }

            public void run() {
                new Builder(ShowUrlActivity.this).setTitle((CharSequence) "\u63d0\u793a").setMessage(this.val$message).setPositiveButton((CharSequence) "\u786e\u5b9a", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ShowUrlActivity.this.mWebView.reload();
                    }
                }).setNegativeButton(BuildConfig.VERSION_NAME, null).show();
            }
        }

        MyWebChromeClient() {
        }

        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d("main", "onJsAlert:" + message);
            ShowUrlActivity.this.runOnUiThread(new AnonymousClass1(message));
            result.confirm();
            return true;
        }
    }

    private class MyWebViewDownLoadListener implements DownloadListener {
        private MyWebViewDownLoadListener() {
        }

        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Log.i("tag", "url=" + url);
            Log.i("tag", "userAgent=" + userAgent);
            Log.i("tag", "contentDisposition=" + contentDisposition);
            Log.i("tag", "mimetype=" + mimetype);
            Log.i("tag", "contentLength=" + contentLength);
            ShowUrlActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
        }
    }

    public ShowUrlActivity() {
        this.url = "http://m.6769c.com";
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_show_url);
        this.mWebView = (WebView) findViewById(R.id.webView);
        if (!TextUtils.isEmpty(getIntent().getStringExtra("url"))) {
            this.url = getIntent().getStringExtra("url");
        }
        initView();
    }

    private void initView() {
        this.webSettings = this.mWebView.getSettings();
        this.mWebView.setWebChromeClient(new MyWebChromeClient());
        this.mWebView.getSettings().setJavaScriptEnabled(true);
        this.webSettings.setDomStorageEnabled(true);
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        this.mWebView.setDownloadListener(new MyWebViewDownLoadListener());
        this.mWebView.loadUrl(this.url);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && this.mWebView.canGoBack()) {
            this.mWebView.goBack();
            return true;
        }
        finish();
        return super.onKeyDown(keyCode, event);
    }
}