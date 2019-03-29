package com.cocodecat.vijoz.adplayer.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cocodecat.vijoz.adplayer.R;
import com.cocodecat.vijoz.adplayer.activity.MainActivity;
import com.cocodecat.vijoz.adplayer.pager.VideoPager;

import es.dmoral.toasty.Toasty;

public class LoginDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private MainActivity mainActivity;
    private TextView tv_setting_ok;
    private TextView tv_setting_cancel;

    private EditText et_code;
    private EditText et_password;

    private VideoPager videoPager;

    public LoginDialog(Context context,VideoPager videoPager,MainActivity mainActivity) {
        super(context, R.style.Dialog_Fullscreen_vijoz);
        this.context = context;
        this.videoPager = videoPager;
        this.mainActivity = mainActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_login);
        //初始化组件
        initView();
        //初始化数据
        initData();
        //初始化点击监听
        initListener();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        tv_setting_ok = findViewById(R.id.tv_sure);
        tv_setting_cancel = findViewById(R.id.tv_cancel);

        et_code = findViewById(R.id.et_code);
        et_password = findViewById(R.id.et_password);
    }

    /**
     * 初始化数据
     */
    private void initData() {

    }

    /**
     * 初始化点击事情
     */
    private void initListener() {
        tv_setting_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_code.getText().toString().trim().equals("666") &&
                        et_password.getText().toString().trim().equals("666")) {
                    videoPager.startPlay();
                    //播放画面
                    LoginDialog.this.dismiss();
                } else {
                    Toasty.error(context, "确认码或密码错误！").show();
                }
            }
        });

        tv_setting_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialog.this.dismiss();
                mainActivity.finish();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }
    }
}
