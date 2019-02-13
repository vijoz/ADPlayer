package com.cocodecat.vijoz.adplayer.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.cocodecat.vijoz.adplayer.R;
import com.cocodecat.vijoz.adplayer.base.Constants;
import com.cocodecat.vijoz.adplayer.utils.SPUtils;
import com.cocodecat.vijoz.adplayer.view.SwitchButton;

public class SettingDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private TextView tv_txt_video;
    private TextView tv_txt_pic;
    private SwitchButton switchButton;


    public SettingDialog(Context context) {
        super(context, R.style.Dialog_Fullscreen_vijoz);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_setting);
        //初始化组件
        initView();
        //初始化点击监听
        initListener();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        tv_txt_video = findViewById(R.id.tv_txt_video);
        tv_txt_pic = findViewById(R.id.tv_txt_pic);
        switchButton = findViewById(R.id.sb_first_play);
        //是否先播放图片
        boolean YNFirstRun = (boolean) SPUtils.get(context, Constants.instance().SP_FirstRun, false);
        if (YNFirstRun) {
            switchButton.setChecked(true);
        } else {
            switchButton.setChecked(false);
        }

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SPUtils.put(context,Constants.instance().SP_FirstRun,b);
                if (b) {
//                    switchButton.setBackColorRes(R.color.base_color_header_green);
                } else {
//                    switchButton.setBackColorRes(R.color.ksw_md_solid_normal);
                }
            }
        });
    }

    /**
     * 初始化点击事情
     */
    private void initListener() {}


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.iv:
            default:
                break;
        }
    }
}
