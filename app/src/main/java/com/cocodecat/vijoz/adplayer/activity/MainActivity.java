package com.cocodecat.vijoz.adplayer.activity;

import android.Manifest;
import android.app.Activity;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.cocodecat.vijoz.adplayer.Fragment.BaseFragment;
import com.cocodecat.vijoz.adplayer.R;
import com.cocodecat.vijoz.adplayer.base.BasePager;
import com.cocodecat.vijoz.adplayer.dialog.LoginDialog;
import com.cocodecat.vijoz.adplayer.dialog.SettingDialog;
import com.cocodecat.vijoz.adplayer.pager.VideoPager;
import com.cocodecat.vijoz.adplayer.utils.SetActivityUtil;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends FragmentActivity implements EasyPermissions.PermissionCallbacks {

    private FrameLayout fl_main_content;
    private RadioGroup rg_bottom_tab;
    private List<BasePager> mBasePagers;
    private Activity mContext;
    public VideoPager videoPager;

    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this;
        //设置Activity全屏显示
        SetActivityUtil.initialize().setFullScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fl_main_content = findViewById(R.id.fl_main_content);
        rg_bottom_tab = findViewById(R.id.rg_bottom_tab);
        mBasePagers = new ArrayList<>();
        videoPager = new VideoPager(this);
        mBasePagers.add(videoPager);
        rg_bottom_tab.setOnCheckedChangeListener(new MyCheckedChangeListener());
        EasyPermissions.requestPermissions(mContext, "允许读取手机文件", 0, Manifest.permission.READ_EXTERNAL_STORAGE);

        //Dialog创建
        LoginDialog loginDialog = new LoginDialog(this, videoPager, this);
        loginDialog.setCanceledOnTouchOutside(false);
        loginDialog.setCancelable(false);
        loginDialog.show();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        //申请成功时调用
        rg_bottom_tab.check(R.id.rb_video);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        //申请失败时调用
        finish();
    }

    private class MyCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_video:
                    position = 0;
                    break;
            }
            setFragment();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void setFragment() {
        final FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        BasePager basePager = getBasePager();
        transaction.replace(R.id.fl_main_content, BaseFragment.newInstance(basePager));
        transaction.commit();
    }

    private BasePager getBasePager() {
        BasePager basePager = mBasePagers.get(position);
        if (basePager != null && !basePager.isInit()) {
            basePager.initData();
        }
        return basePager;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * 是否已经退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (position != 0) {//不是第一页面
                position = 0;
                rg_bottom_tab.check(R.id.rb_video);//首页
                return true;
            } else {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
