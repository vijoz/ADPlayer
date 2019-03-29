package com.cocodecat.vijoz.adplayer.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.cocodecat.vijoz.adplayer.R;
import com.cocodecat.vijoz.adplayer.base.Constants;
import com.cocodecat.vijoz.adplayer.base.MyApplication;
import com.cocodecat.vijoz.adplayer.utils.SPUtils;
import com.cocodecat.vijoz.adplayer.view.SwitchButton;

public class SettingDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private TextView tv_setting_ok;
    private TextView tv_setting_cancel;
    private SwitchButton switchUDish;
    private SwitchButton switchTwo;
    private Button btn_rotate;

    private EditText et_time_value;


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
        tv_setting_ok = findViewById(R.id.tv_sure);
        tv_setting_cancel = findViewById(R.id.tv_cancel);
        et_time_value = findViewById(R.id.et_time_value);

        switchUDish = findViewById(R.id.sb_u_dish);
        switchTwo = findViewById(R.id.sb_two);

        btn_rotate = findViewById(R.id.btn_rotate);

        et_time_value.setText((Integer) SPUtils.get(context, Constants.instance().SP_Pic_ChangeTime, 10) + "");

        //是否先播放图片
        boolean YNUDish = (boolean) SPUtils.get(context, Constants.instance().SP_U_Dish, false);
        if (YNUDish) {
            switchUDish.setChecked(true);
        } else {
            switchUDish.setChecked(false);
        }

        boolean YNTwo = (boolean) SPUtils.get(context, Constants.instance().SP_TWO, false);
        if (YNTwo) {
            switchTwo.setChecked(true);
        } else {
            switchTwo.setChecked(false);
        }
    }

    /**
     * 初始化点击事情
     */
    private void initListener() {
        switchUDish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SPUtils.put(context, Constants.instance().SP_U_Dish, b);
            }
        });

        switchTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPUtils.put(context, Constants.instance().SP_TWO, isChecked);
            }
        });

        tv_setting_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int changeTime = Integer.parseInt(et_time_value.getText().toString().trim());
                SPUtils.put(context, Constants.instance().SP_Pic_ChangeTime, changeTime);
                MyApplication.getInstance().picTime = changeTime;
                SettingDialog.this.dismiss();
            }
        });

        tv_setting_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingDialog.this.dismiss();
            }
        });


        btn_rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPortrait) {
                    isPortrait = false;
                    ((Activity) context).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    isPortrait = true;
                    ((Activity) context).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });
    }

    boolean isPortrait = true;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.iv:
            default:
                break;
        }
    }
}
