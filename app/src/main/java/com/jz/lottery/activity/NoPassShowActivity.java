package com.jz.lottery.activity;

/**
 * Created by cheng on 2017/7/25.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;
import com.jz.lottery.base.BaseActivity;
import com.jz.lottery.base.BaseAdapter;
import com.jz.lottery.view.DrawableCenterButton;

import java.util.ArrayList;

public class NoPassShowActivity extends BaseActivity implements OnClickListener {
    RelativeLayout LogAc_contents;
    RelativeLayout banbengengxin;
    private ArrayList<String> data;
    private ArrayList<Entity> entities;
    private LinearLayout faxianshow;
    ListView kaijiang_list;
    private LinearLayout kaijiangshow;
    ListAdapter listAdapter;
    private Button login_bt_login;
    WebView mWebView;
    private RadioGroup maingroup;
    private LinearLayout mainline1;
    private DrawableCenterButton mainradiohome;
    private DrawableCenterButton mainradiosearch;
    private DrawableCenterButton mainradioself;
    private DrawableCenterButton mainradiozzzs;
    RelativeLayout qingchuhuancun;
    private LinearLayout shouyeshow;
    TextView textview_cache;
    private String url;
    private String urlfaxian;
    WebSettings webSettings;
    WebSettings webSettingsTwo;
    private WebView webViewfaxian;
    RelativeLayout yijianfankui;

    public static class Entity {
        private String haoma;
        private int img;
        private String name;
        private String qishu;
        private String time;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Entity(int img, String name, String qishu, String haoma, String time) {
            this.img = img;
            this.name = name;
            this.qishu = qishu;
            this.haoma = haoma;
            this.time = time;
        }

        public int getImg() {
            return this.img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public String getQishu() {
            return this.qishu;
        }

        public void setQishu(String qishu) {
            this.qishu = qishu;
        }

        public String getHaoma() {
            return this.haoma;
        }

        public void setHaoma(String haoma) {
            this.haoma = haoma;
        }

        public String getTime() {
            return this.time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public class ListAdapter extends BaseAdapter<Entity> {
        private RelativeLayout LiveRoomrl;
        private TextView courseclassbegin;
        private TextView coursename;
        private TextView coursestatus;
        private TextView line;
        private ArrayList<Entity> mData;
        private Context mcontext;
        private TextView qihao;
        private TextView time;
        private ImageView userimg;
        private TextView username;

        public ListAdapter(ArrayList<Entity> mData, Context mcontext) {
            super(mData, mcontext, R.layout.list_item);
            this.mcontext = mcontext;
            this.mData = mData;
        }

        public void setView(ViewHolder vh, Entity entity, int position) {
            this.time = (TextView) vh.findById(R.id.time);
            this.qihao = (TextView) vh.findById(R.id.qihao);
            this.userimg = (ImageView) vh.findById(R.id.user_img);
            this.username = (TextView) vh.findById(R.id.user_name);
            this.line = (TextView) vh.findById(R.id.line);
            this.coursename = (TextView) vh.findById(R.id.course_name);
            this.coursestatus = (TextView) vh.findById(R.id.course_status);
            this.courseclassbegin = (TextView) vh.findById(R.id.course_class_begin);
            this.LiveRoomrl = (RelativeLayout) vh.findById(R.id.LiveRoom_rl);
            this.userimg.setImageDrawable(NoPassShowActivity.this.getResources().getDrawable(entity.getImg()));
            this.coursename.setText(entity.getName());
            this.coursestatus.setText(entity.getHaoma());
            this.qihao.setText(entity.getQishu());
            this.time.setText(entity.getTime());
        }
    }

    class MyWebChromeClient extends WebChromeClient {

        /* renamed from: com.wwp.www.vrcpchaxun.activity.NoPassShowActivity.MyWebChromeClient.1 */
        class AnonymousClass1 implements Runnable {
            final /* synthetic */ String val$message;

            AnonymousClass1(String str) {
                this.val$message = str;
            }

            public void run() {
                new Builder(NoPassShowActivity.this).setTitle((CharSequence) "\u63d0\u793a").setMessage(this.val$message).setPositiveButton((CharSequence) "\u786e\u5b9a", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        NoPassShowActivity.this.mWebView.reload();
                    }
                }).setNegativeButton(BuildConfig.VERSION_NAME, null).show();
            }
        }

        MyWebChromeClient() {
        }

        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d("main", "onJsAlert:" + message);
            NoPassShowActivity.this.runOnUiThread(new AnonymousClass1(message));
            result.confirm();
            return true;
        }
    }

    public NoPassShowActivity() {
        this.data = new ArrayList();
        this.entities = new ArrayList();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_no_pass_show);
        this.mWebView = (WebView) findViewById(R.id.webView);
        initialize();
        initView();
    }

    private void initView() {
        this.entities.add(new Entity(R.drawable.liuhe, "\u516d\u5408\u5f69", "\u7b2c20170603094\u671f", "23 41 37 11 49", "4\u5206\u949f"));
        this.entities.add(new Entity(R.drawable.pk, "pk\u62fe", "\u7b2c621331\u671f", "04 07 09 01 02 05 04", "3\u5206\u949f"));
        this.entities.add(new Entity(R.drawable.sanfen, "\u4e09\u5206\u5f69", "\u7b2c20170603094\u671f", "6 3 5 7 9", "2\u5206\u949f"));
        this.entities.add(new Entity(R.drawable.sanfenpk, "\u4e09\u5206pk\u62fe", "\u7b2c20170603094\u671f", "04 09 05 01 02 04 07", "5\u5206\u949f"));
        this.entities.add(new Entity(R.drawable.shishicai, "\u65f6\u65f6\u5f69", "\u7b2c20170603109\u671f", "07 04 05 0View.GONE 09 02 01", "5\u5206\u949f"));
        this.entities.add(new Entity(R.drawable.xingyun, "\u5e78\u8fd028", "\u7b2c20170603096\u671f", "6 3 5 1 7", "3\u5206\u949f"));
        this.url = "http://www.263991View.GONE.com/";
        this.urlfaxian = "http://www.bwlc.net/";
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
        this.webSettingsTwo = this.webViewfaxian.getSettings();
        this.webSettingsTwo.setUseWideViewPort(true);
        this.webSettingsTwo.setLoadWithOverviewMode(true);
        this.webSettingsTwo.setJavaScriptEnabled(true);
        this.webViewfaxian.setWebChromeClient(new MyWebChromeClient());
        this.webViewfaxian.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        this.webViewfaxian.loadUrl(this.urlfaxian);
        this.mWebView.loadUrl(this.url);
        this.listAdapter = new ListAdapter(this.entities, this);
        this.kaijiang_list.setAdapter(this.listAdapter);
    }

    private void initialize() {
        this.qingchuhuancun = (RelativeLayout) findViewById(R.id.qingchuhuancun);
        this.textview_cache = (TextView) findViewById(R.id.textview_cache);
        this.banbengengxin = (RelativeLayout) findViewById(R.id.banbengengxin);
        this.yijianfankui = (RelativeLayout) findViewById(R.id.yijianfankui);
        this.LogAc_contents = (RelativeLayout) findViewById(R.id.LogAc_contents);
        this.kaijiang_list = (ListView) findViewById(R.id.kaijiang_list);
        this.webViewfaxian = (WebView) findViewById(R.id.webView_faxian);
        this.shouyeshow = (LinearLayout) findViewById(R.id.shouye_show);
        this.kaijiangshow = (LinearLayout) findViewById(R.id.kaijiang_show);
        this.webViewfaxian = (WebView) findViewById(R.id.webView_faxian);
        this.faxianshow = (LinearLayout) findViewById(R.id.faxian_show);
        this.mainradiohome = (DrawableCenterButton) findViewById(R.id.main_radio_home);
        this.mainradiozzzs = (DrawableCenterButton) findViewById(R.id.main_radio_zzzs);
        this.mainradiosearch = (DrawableCenterButton) findViewById(R.id.main_radio_search);
        this.mainradioself = (DrawableCenterButton) findViewById(R.id.main_radio_self);
        this.maingroup = (RadioGroup) findViewById(R.id.main_group);
        this.mainline1 = (LinearLayout) findViewById(R.id.main_line1);
        this.mainradiohome.setOnClickListener(this);
        this.mainradiozzzs.setOnClickListener(this);
        this.mainradiosearch.setOnClickListener(this);
        this.mainradioself.setOnClickListener(this);
        this.qingchuhuancun.setOnClickListener(this);
        this.banbengengxin.setOnClickListener(this);
        this.yijianfankui.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_radio_home /*2131427425*/:
                this.shouyeshow.setVisibility(View.VISIBLE);
                this.kaijiangshow.setVisibility(View.GONE);
                this.faxianshow.setVisibility(View.GONE);
                this.LogAc_contents.setVisibility(View.GONE);
            case R.id.main_radio_zzzs /*2131427426*/:
                this.shouyeshow.setVisibility(View.GONE);
                this.kaijiangshow.setVisibility(View.VISIBLE);
                this.faxianshow.setVisibility(View.GONE);
                this.LogAc_contents.setVisibility(View.GONE);
            case R.id.main_radio_search /*2131427427*/:
                this.shouyeshow.setVisibility(View.GONE);
                this.kaijiangshow.setVisibility(View.GONE);
                this.faxianshow.setVisibility(View.VISIBLE);
                this.LogAc_contents.setVisibility(View.GONE);
            case R.id.main_radio_self /*213142742View.GONE*/:
                this.shouyeshow.setVisibility(View.GONE);
                this.kaijiangshow.setVisibility(View.GONE);
                this.faxianshow.setVisibility(View.GONE);
                this.LogAc_contents.setVisibility(View.VISIBLE);
            case R.id.qingchuhuancun /*2131427452*/:
                this.textview_cache.setText("0M");
                Toast.makeText(this, "\u6e05\u9664\u6210\u529f", Toast.LENGTH_SHORT).show();
            case R.id.banbengengxin /*2131427454*/:
                Toast.makeText(this, "\u5df2\u662f\u6700\u65b0\u7248\u672c", Toast.LENGTH_SHORT).show();
            case R.id.yijianfankui /*2131427456*/:
                startActivity(new Intent(this, LSHSuggestionFeedBackActivity.class));
            default:
        }
    }
}