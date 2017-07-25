package com.jz.lottery.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;
import com.jz.lottery.base.BaseActivity;
import com.jz.lottery.dialog.LSHCustomDialog;

import static rx.internal.operators.OnSubscribeConcatMap.END;

/**
 * Created by cheng on 2017/7/25.
 */

public class LSHSuggestionFeedBackActivity extends BaseActivity implements View.OnClickListener, LSHCustomDialog.LSHCustomDialogClickListenerInterface {


    private String mDialogType;
    private EditText mEditTextContactInformation;
    private EditText mEditTextSuggestion;
    private LSHCustomDialog mLSHCustomDialog;
    private ImageView mTextViewBack;
    private TextView mTextViewSubmit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_lshsuggestion_feed_back);
        init();
    }

    private void init() {
        this.mTextViewBack = (ImageView) findViewById(R.id.LSHSuggestionFeedBackAc_tv_back);
        this.mTextViewBack.setOnClickListener(this);
        this.mTextViewSubmit = (TextView) findViewById(R.id.LSHSuggestionFeedBackAc_tv_submit);
        this.mTextViewSubmit.setOnClickListener(this);
        this.mEditTextSuggestion = (EditText) findViewById(R.id.LSHSuggestionFeedBackAc_edit_suggestion);
        this.mEditTextContactInformation = (EditText) findViewById(R.id.LSHSuggestionFeedBackAc_edit_contact_information);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.LSHSuggestionFeedBackAc_tv_back /*2131427434*/:
                if (TextUtils.isEmpty(this.mEditTextSuggestion.getText().toString().trim())) {
                    finish();
                    return;
                }
                if (this.mLSHCustomDialog != null) {
                    this.mLSHCustomDialog.setDialogText("\u786e\u5b9a\u53d6\u6d88\u7f16\u8f91\uff1f", "\u53d6\u6d88", "\u786e\u5b9a");
                } else {
                    this.mLSHCustomDialog = new LSHCustomDialog(this, "\u786e\u5b9a\u53d6\u6d88\u7f16\u8f91\uff1f", "\u53d6\u6d88", "\u786e\u5b9a");
                    this.mLSHCustomDialog.setClicklistener(this);
                }
                this.mDialogType = "finish";
                this.mLSHCustomDialog.show();
            case R.id.LSHSuggestionFeedBackAc_tv_submit /*2131427436*/:
                String suggestion = this.mEditTextSuggestion.getText().toString().trim();
                String contactInformation = this.mEditTextContactInformation.getText().toString().trim();
                if (TextUtils.isEmpty(suggestion)) {
                    if (this.mLSHCustomDialog != null) {
                        this.mLSHCustomDialog.setDialogText("\u8bf7\u586b\u5199\u60a8\u7684\u53cd\u9988\u610f\u89c1\u518d\u63d0\u4ea4\uff01", BuildConfig.VERSION_NAME, "\u786e\u5b9a");
                    } else {
                        this.mLSHCustomDialog = new LSHCustomDialog(this, "\u8bf7\u586b\u5199\u60a8\u7684\u53cd\u9988\u610f\u89c1\u518d\u63d0\u4ea4\uff01", BuildConfig.VERSION_NAME, "\u786e\u5b9a");
                        this.mLSHCustomDialog.setClicklistener(this);
                    }
                    this.mDialogType = "suggestion";
                    this.mLSHCustomDialog.show();
                    return;
                }
                Toast.makeText(this, "\u63d0\u4ea4\u6210\u529f", Toast.LENGTH_SHORT).show();
            default:
        }
    }

    public void doConfirm() {
        String str = this.mDialogType;
        int obj = -1;
        switch (str.hashCode()) {
            case -1274442605:
                if (str.equals("finish")) {
                    obj = 2;
                    break;
                }
                break;
            case 103149417:
                if (str.equals("login")) {
                    obj = 1;
                    break;
                }
                break;
            case 1197722116:
                if (str.equals("suggestion")) {
                    obj = 3;
                    break;
                }
                break;
        }
        switch (obj) {
            case END /*2*/:
                finish();
            default:
        }
    }

    public void doCancel() {
    }

    public void onBackPressed() {
        if (TextUtils.isEmpty(this.mEditTextSuggestion.getText().toString().trim())) {
            finish();
            return;
        }
        if (this.mLSHCustomDialog != null) {
            this.mLSHCustomDialog.setDialogText("\u786e\u5b9a\u53d6\u6d88\u7f16\u8f91\uff1f", "\u53d6\u6d88", "\u786e\u5b9a");
        } else {
            this.mLSHCustomDialog = new LSHCustomDialog(this, "\u786e\u5b9a\u53d6\u6d88\u7f16\u8f91\uff1f", "\u53d6\u6d88", "\u786e\u5b9a");
            this.mLSHCustomDialog.setClicklistener(this);
        }
        this.mDialogType = "finish";
        this.mLSHCustomDialog.show();
    }
}
