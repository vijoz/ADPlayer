package com.cocodecat.vijoz.adplayer.base;

import android.content.Context;
import android.view.View;

import com.google.gson.Gson;

public abstract class BasePager{

    protected Context mContext;
    protected View mRootView;
    protected boolean isInit;
    protected Gson mGson;

    public BasePager(Context context) {
        mContext = context;
        mRootView=initView();
        MyApplication myApplication= (MyApplication) context.getApplicationContext();
        mGson=myApplication.getGson();
    }

    public View getRootView() {
        return mRootView;
    }

    public boolean isInit() {
        return isInit;
    }

    /**
     * 初始化布局
     * @return
     */
    public abstract View initView();

    /**
     * 初始化数据
     */
    public void initData(){
        isInit=true;
    }
}
