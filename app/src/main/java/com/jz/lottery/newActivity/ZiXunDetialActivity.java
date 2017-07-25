package com.jz.lottery.newActivity;

/**
 * Created by cheng on 2017/7/25.
 */

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;
import com.jz.lottery.base.BaseActivity;
import com.jz.lottery.entity.ZixunDetialEntity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.URL;


public class ZiXunDetialActivity extends BaseActivity implements OnClickListener {
    private RelativeLayout LSHSuggestionFeedBackActitle;
    private ImageView LSHSuggestionFeedBackActvback;
    private TextView LSHSuggestionFeedBackActvtitle;
    private WebView body;
    private Callback.Cancelable cancelable;
    private TextView detialtitle;
    ImageGetter imgGetter;
    private String mID;
    private RequestParams requestParams;
    private TextView time;
    private ZixunDetialEntity zixunDetialEntity;

    public ZiXunDetialActivity() {
        this.zixunDetialEntity = new ZixunDetialEntity();
        this.mID = BuildConfig.VERSION_NAME;
        this.imgGetter = new ImageGetter() {
            public Drawable getDrawable(String source) {
                Log.i("RG", "source---?>>>" + source);
                try {
                    URL url = new URL(source);
                    Log.i("RG", "url---?>>>" + url);
                    Drawable drawable = Drawable.createFromStream(url.openStream(), BuildConfig.VERSION_NAME);
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    Log.i("RG", "url---?>>>" + url);
                    return drawable;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_zi_xun_detial);
        this.mID = getIntent().getStringExtra("id");
        initialize();
        getzixunDetial();
    }

    private void initialize() {
        this.LSHSuggestionFeedBackActvback = (ImageView) findViewById(R.id.LSHSuggestionFeedBackAc_tv_back);
        this.LSHSuggestionFeedBackActvtitle = (TextView) findViewById(R.id.LSHSuggestionFeedBackAc_tv_title);
        this.LSHSuggestionFeedBackActitle = (RelativeLayout) findViewById(R.id.LSHSuggestionFeedBackAc_title);
        this.detialtitle = (TextView) findViewById(R.id.detial_title);
        this.time = (TextView) findViewById(R.id.time);
        this.body = (WebView) findViewById(R.id.body);
        this.LSHSuggestionFeedBackActvback.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LSHSuggestionFeedBackAc_tv_back /*2131427434*/:
                finish();
            default:
        }
    }

    private void getzixunDetial() {
        this.requestParams = null;
        this.requestParams = new RequestParams("http://www.wozhongla.com/news/API/JSON/view.php?aid=" + this.mID);
        this.cancelable = x.http().get(this.requestParams, new Callback.CommonCallback<String>() {
            public void onSuccess(String result) {
                System.out.println("WWW---" + result);
                ZiXunDetialActivity.this.zixunDetialEntity = (ZixunDetialEntity) new Gson().fromJson(result, ZixunDetialEntity.class);
                if (ZiXunDetialActivity.this.zixunDetialEntity != null) {
                    ZiXunDetialActivity.this.detialtitle.setText(ZiXunDetialActivity.this.zixunDetialEntity.getTitle());
                    ZiXunDetialActivity.this.time.setText(ZiXunDetialActivity.this.zixunDetialEntity.getPubdate());
                    ZiXunDetialActivity.this.body.loadDataWithBaseURL(null, ZiXunDetialActivity.this.zixunDetialEntity.getBody(), "text/html", "utf-8", null);
                    ZiXunDetialActivity.this.body.getSettings().setJavaScriptEnabled(true);
                    ZiXunDetialActivity.this.body.setWebChromeClient(new WebChromeClient());
                }
            }

            public void onError(Throwable ex, boolean isOnCallback) {
                System.out.println("WWW---" + ex.toString());
            }

            public void onCancelled(CancelledException cex) {
            }

            public void onFinished() {
            }
        });
    }
}