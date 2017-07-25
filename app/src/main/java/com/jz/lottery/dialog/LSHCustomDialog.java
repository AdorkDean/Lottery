package com.jz.lottery.dialog;

/**
 * Created by cheng on 2017/7/25.
 */

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import com.jz.lottery.BuildConfig;
import com.jz.lottery.R;


public class LSHCustomDialog extends Dialog {
    private String cacelButtonText;
    private LSHCustomDialogClickListenerInterface clickListenerInterface;
    private String confirmButtonText;
    private Context context;
    private String title;
    private TextView tvCancel;
    private TextView tvConfirm;
    private TextView tvLine;
    private TextView tvTitle;

    public interface LSHCustomDialogClickListenerInterface {
        void doCancel();

        void doConfirm();
    }

    private class clickListener implements View.OnClickListener {
        private clickListener() {
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.lsh_custom_dialog_tv_cancle /*2131427468*/:
                    LSHCustomDialog.this.dismiss();
                    LSHCustomDialog.this.clickListenerInterface.doCancel();
                case R.id.lsh_custom_dialog_tv_confirm /*2131427470*/:
                    LSHCustomDialog.this.dismiss();
                    LSHCustomDialog.this.clickListenerInterface.doConfirm();
                default:
            }
        }
    }

    public LSHCustomDialog(Context context) {
        super(context);
        this.title = BuildConfig.VERSION_NAME;
        this.confirmButtonText = BuildConfig.VERSION_NAME;
        this.cacelButtonText = BuildConfig.VERSION_NAME;
        requestWindowFeature(1);
        this.context = context;
        init();
    }

    public LSHCustomDialog(Context context, String title, String cacelButtonText, String confirmButtonText) {
        super(context);
        this.title = BuildConfig.VERSION_NAME;
        this.confirmButtonText = BuildConfig.VERSION_NAME;
        this.cacelButtonText = BuildConfig.VERSION_NAME;
        requestWindowFeature(1);
        this.context = context;
        this.title = title;
        this.confirmButtonText = confirmButtonText;
        this.cacelButtonText = cacelButtonText;
        init();
    }

    public void init() {
        View view = LayoutInflater.from(this.context).inflate(R.layout.dialog_lsh_custom_dialog_layout, null);
        setContentView(view);
        this.tvTitle = (TextView) view.findViewById(R.id.lsh_custom_dialog_tv_title);
        this.tvConfirm = (TextView) view.findViewById(R.id.lsh_custom_dialog_tv_confirm);
        this.tvCancel = (TextView) view.findViewById(R.id.lsh_custom_dialog_tv_cancle);
        this.tvLine = (TextView) view.findViewById(R.id.lsh_custom_dialog_tv_line);
        this.tvTitle.setText(this.title);
        this.tvConfirm.setText(this.confirmButtonText);
        this.tvCancel.setText(this.cacelButtonText);
        if (this.cacelButtonText.equals(BuildConfig.VERSION_NAME)) {
            this.tvCancel.setVisibility(8);
            this.tvLine.setVisibility(8);
        }
        this.tvConfirm.setOnClickListener(new clickListener());
        this.tvCancel.setOnClickListener(new clickListener());
        Window dialogWindow = getWindow();
        LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (((double) this.context.getResources().getDisplayMetrics().widthPixels) * 0.9d);
        dialogWindow.setAttributes(lp);
    }

    public void setDialogText(String title, String cancle, String confirm) {
        if (cancle.equals(BuildConfig.VERSION_NAME)) {
            this.tvCancel.setVisibility(8);
            this.tvLine.setVisibility(8);
        } else {
            this.tvCancel.setVisibility(0);
            this.tvLine.setVisibility(0);
        }
        this.tvTitle.setText(title);
        this.tvCancel.setText(cancle);
        this.tvConfirm.setText(confirm);
    }

    public void setClicklistener(LSHCustomDialogClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    public void show() {
        if (!isShowing()) {
            super.show();
        }
    }
}