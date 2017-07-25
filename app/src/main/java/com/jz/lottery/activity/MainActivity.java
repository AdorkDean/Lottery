package com.jz.lottery.activity;

/**
 * Created by cheng on 2017/7/25.
 */


import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;
import com.jz.lottery.base.BaseActivity;
import com.jz.lottery.entity.Config;
import com.jz.lottery.view.LSHPopWindowUpdateUserInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.jpush.android.api.JPushInterface;


public class MainActivity extends BaseActivity {
    private Callback.Cancelable cancelable;
    private WebView mWebView;
    private LSHPopWindowUpdateUserInfo popWindowUpdateUserInfo;
    private RequestParams requestParams;
    private String url;
    WebSettings webSettings;

    class MyWebChromeClient extends WebChromeClient {

        /* renamed from: com.wwp.www.vrcpchaxun.activity.MainActivity.MyWebChromeClient.1 */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ String val$message;

            AnonymousClass1(String str) {
                this.val$message = str;
            }

            public void run() {
                new Builder(MainActivity.this).setTitle((CharSequence) "\u63d0\u793a").setMessage(this.val$message).setPositiveButton((CharSequence) "\u786e\u5b9a", new OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.mWebView.reload();
                    }
                }).setNegativeButton(BuildConfig.VERSION_NAME, null).show();
            }
        }

        MyWebChromeClient() {
        }

        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d("main", "onJsAlert:" + message);
            MainActivity.this.runOnUiThread(new AnonymousClass1(message));
            result.confirm();
            return true;
        }
    }

    public MainActivity() {
        this.url = BuildConfig.VERSION_NAME;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        this.mWebView = (WebView) findViewById(R.id.webView);
        this.mWebView.post(new Runnable() {
            public void run() {
                MainActivity.this.showUpdatePopWindow();
            }
        });
        new BmobQuery().getObject("bZtHPGGP", new QueryListener<Config>() {
            public void done(Config config, BmobException e) {
                if (e == null) {
                    if (config.isShow()) {
                        Intent intent = new Intent(MainActivity.this, LogoActivity.class);
                        intent.putExtra("url", config.getUrl());
                        MainActivity.this.startActivity(intent);
                        MainActivity.this.finish();
                    } else {
                        MainActivity.this.startActivity(new Intent(MainActivity.this, NoPassActivity.class));
                        MainActivity.this.finish();
                    }
                    System.out.println("WWW---\u67e5\u8be2\u6210\u529f" + config.toString());
                    return;
                }
                Toast.makeText(MainActivity.this, "\u7f51\u7edc\u9519\u8bef", Toast.LENGTH_SHORT).show();
                System.out.println("WWW---\u67e5\u8be2\u5931\u8d25\uff1a" + e.getMessage());
            }
        });
    }

    private void initView() {
        this.url = "http://god1.256vvv.com/apk.php";
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

    private void showUpdatePopWindow() {
        this.popWindowUpdateUserInfo = new LSHPopWindowUpdateUserInfo(this);
        this.popWindowUpdateUserInfo.setWidth(-1);
        this.popWindowUpdateUserInfo.setHeight(-1);
        this.popWindowUpdateUserInfo.setTouchable(true);
        this.popWindowUpdateUserInfo.setFocusable(true);
        this.popWindowUpdateUserInfo.setOutsideTouchable(true);
        this.popWindowUpdateUserInfo.popWindowShow(this.mWebView);
        this.popWindowUpdateUserInfo.setTextViewText("\u6b63\u5728\u52a0\u8f7d\u6570\u636e");
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    private void getCollegeParamsList() {
        this.requestParams = null;
        this.requestParams = new RequestParams("http://202.61.86.219/Lottery_server/check_and_get_url.php");
        this.requestParams.addBodyParameter("appid", "2017071923");
        this.requestParams.addBodyParameter("type", "android");
        this.cancelable = x.http().get(this.requestParams, new Callback.CommonCallback<String>() {
            public void onSuccess(String result) {
                JSONObject jSONObject;
                JSONException e;
                if (MainActivity.this.popWindowUpdateUserInfo != null && MainActivity.this.popWindowUpdateUserInfo.isShowing()) {
                    MainActivity.this.popWindowUpdateUserInfo.dismiss();
                }
                System.out.println("WWW-3-" + result);
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    try {
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        String isshowwap = jsonObject1.getString("show_url");
                        if (isshowwap.equals("1")) {
                            Intent intent = new Intent(MainActivity.this, LogoActivity.class);
                            intent.putExtra("url", jsonObject1.getString("url"));
                            MainActivity.this.startActivity(intent);
                            MainActivity.this.finish();
                        } else if (isshowwap.equals("0")) {
                            MainActivity.this.startActivity(new Intent(MainActivity.this, NoPassActivity.class));
                            MainActivity.this.finish();
                        }
                        jSONObject = jsonObject;
                    } catch (JSONException e2) {
                        e = e2;
                        jSONObject = jsonObject;
                        e.printStackTrace();
                    }
                } catch (JSONException e3) {
                    e = e3;
                    e.printStackTrace();
                }
            }

            public void onError(Throwable ex, boolean isOnCallback) {
                if (MainActivity.this.popWindowUpdateUserInfo != null && MainActivity.this.popWindowUpdateUserInfo.isShowing()) {
                    MainActivity.this.popWindowUpdateUserInfo.dismiss();
                }
            }

            public void onCancelled(CancelledException cex) {
            }

            public void onFinished() {
            }
        });
    }
}