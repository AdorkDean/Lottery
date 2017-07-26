package com.jz.lottery.activity;

/**
 * Created by cheng on 2017/7/25.
 */


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;
import com.jz.lottery.base.BaseActivity;
import com.jz.lottery.view.LSHPopWindowUpdateUserInfo;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class MainActivity extends BaseActivity {
    private Callback.Cancelable cancelable;
    private LSHPopWindowUpdateUserInfo popWindowUpdateUserInfo;
    private RequestParams requestParams;
    private String url;
    private View view;

    public MainActivity() {
        this.url = BuildConfig.VERSION_NAME;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_main);
        view = findViewById(R.id.activity_main);
//        view.post(new Runnable() {
//            @Override
//            public void run() {
//                MainActivity.this.showUpdatePopWindow();
//            }
//        });
        MainActivity.this.startActivity(new Intent(MainActivity.this, NoPassActivity.class));
        MainActivity.this.finish();
//        new BmobQuery().getObject("bZtHPGGP", new QueryListener<Config>() {
//            public void done(Config config, BmobException e) {
//                if (e == null) {
//                    if (!config.isShow()) {
//                        Intent intent = new Intent(MainActivity.this, LogoActivity.class);
//                        intent.putExtra("url", config.getUrl());
//                        MainActivity.this.startActivity(intent);
//                        MainActivity.this.finish();
//                    } else {
//                        MainActivity.this.startActivity(new Intent(MainActivity.this, NoPassActivity.class));
//                        MainActivity.this.finish();
//                    }
//                    return;
//                }
//                Toast.makeText(MainActivity.this, "\u7f51\u7edc\u9519\u8bef", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    private void showUpdatePopWindow() {
        this.popWindowUpdateUserInfo = new LSHPopWindowUpdateUserInfo(this);
        this.popWindowUpdateUserInfo.setWidth(-1);
        this.popWindowUpdateUserInfo.setHeight(-1);
        this.popWindowUpdateUserInfo.setTouchable(true);
        this.popWindowUpdateUserInfo.setFocusable(true);
        this.popWindowUpdateUserInfo.setOutsideTouchable(true);
        this.popWindowUpdateUserInfo.popWindowShow(this.view);
        this.popWindowUpdateUserInfo.setTextViewText("\u6b63\u5728\u52a0\u8f7d\u6570\u636e");
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