package com.jz.lottery.newFragment;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;
import com.jz.lottery.base.BaseFragment;

public class FirstFragment extends BaseFragment {
    WebView mWebView;
    private String url;
    View view;
    WebSettings webSettings;

    class MyWebChromeClient extends WebChromeClient {

        /* renamed from: com.wwp.www.vrcpchaxun.newFragment.FirstFragment.MyWebChromeClient.1 */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ String val$message;

            AnonymousClass1(String str) {
                this.val$message = str;
            }

            public void run() {
                new Builder(FirstFragment.this.getActivity()).setTitle((CharSequence) "\u63d0\u793a").setMessage(this.val$message).setPositiveButton((CharSequence) "\u786e\u5b9a", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        FirstFragment.this.mWebView.reload();
                    }
                }).setNegativeButton(BuildConfig.VERSION_NAME, null).show();
            }
        }

        MyWebChromeClient() {
        }

        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d("main", "onJsAlert:" + message);
            FirstFragment.this.getActivity().runOnUiThread(new AnonymousClass1(message));
            result.confirm();
            return true;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_first, container, false);
        return this.view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isNetworkConnected(getActivity())) {
            initView();
        } else {
            Toast.makeText(getActivity(), "\u7f51\u7edc\u4e0d\u53ef\u7528\u8bf7\u68c0\u67e5\u7f51\u7edc\u72b6\u6001", 0).show();
        }
    }

    public void onResume() {
        super.onResume();
        if (isNetworkConnected(getActivity())) {
            initView();
        } else {
            Toast.makeText(getActivity(), "\u7f51\u7edc\u4e0d\u53ef\u7528\u8bf7\u68c0\u67e5\u7f51\u7edc\u72b6\u6001", 0).show();
        }
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            NetworkInfo mNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    private void initView() {
        this.mWebView = (WebView) this.view.findViewById(R.id.webView);
        this.url = "http://www.2639918.com/";
        this.webSettings = this.mWebView.getSettings();
        this.webSettings.setUseWideViewPort(true);
        this.webSettings.setLoadWithOverviewMode(true);
        this.webSettings.setJavaScriptEnabled(true);
        this.mWebView.setWebChromeClient(new MyWebChromeClient());
        this.mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        this.mWebView.loadUrl(this.url);
    }
}