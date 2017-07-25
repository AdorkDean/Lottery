package com.jz.lottery.utils;

/**
 * Created by cheng on 2017/7/25.
 */

import android.text.Editable;
import android.text.TextUtils.TruncateAt;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.TextView;

public class StretchUtil {
    private TextView arrowImage;
    private String down;
    private int lines;
    private TextView textView;
    private String up;

    public StretchUtil(TextView textView, int lines, TextView arrowImage, String up, String down) {
        this.textView = textView;
        this.lines = lines;
        this.arrowImage = arrowImage;
        this.up = up;
        this.down = down;
    }

    public static StretchUtil getInstance(TextView textView, int lines, TextView arrowImage, String up, String down) {
        return new StretchUtil(textView, lines, arrowImage, up, down);
    }

    public void initStretch() {
        this.textView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            boolean flag;

            {
                this.flag = false;
            }

            public void onGlobalLayout() {
                if (!this.flag) {
                    this.flag = true;
                    StretchUtil.this.initStretchStatus();
                }
            }
        });
        this.textView.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                StretchUtil.this.initStretchStatus();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                StretchUtil.this.initStretchStatus();
            }

            public void afterTextChanged(Editable s) {
                StretchUtil.this.initStretchStatus();
            }
        });
        this.arrowImage.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Object o = v.getTag();
                if (o != null) {
                    int status = ((Integer) o).intValue();
                    if (status == 0) {
                        StretchUtil.this.setStretch(1);
                    } else if (status == 1) {
                        StretchUtil.this.setStretch(0);
                    }
                }
            }
        });
    }

    public void initStretchStatus() {
        if (isMoreLines()) {
            setStretch(1);
        } else {
            setStretch(-1);
        }
    }

    public boolean isMoreLines() {
        return this.textView.getPaint().measureText(this.textView.getText().toString()) > ((float) (((this.textView.getWidth() - this.textView.getPaddingLeft()) - this.textView.getPaddingRight()) * this.lines));
    }

    public void setStretch(int status) {
        if (status == -1) {
            this.arrowImage.setVisibility(8);
        } else if (status == 0) {
            this.arrowImage.setVisibility(0);
            this.arrowImage.setText(this.up);
            this.textView.setSingleLine(false);
            this.textView.setEllipsize(null);
        } else if (status == 1) {
            this.arrowImage.setVisibility(0);
            this.arrowImage.setText(this.down);
            this.textView.setLines(this.lines);
            this.textView.setEllipsize(TruncateAt.END);
        }
        this.arrowImage.setTag(Integer.valueOf(status));
    }
}