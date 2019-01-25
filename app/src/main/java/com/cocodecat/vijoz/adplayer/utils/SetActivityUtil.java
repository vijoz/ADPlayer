package com.cocodecat.vijoz.adplayer.utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class SetActivityUtil {

    private static final Object LockThis = new Object();
    private static SetActivityUtil setActivityUtil;

    //工具类初始化
    public synchronized static SetActivityUtil initialize() {
        if (null == setActivityUtil) {
            synchronized (LockThis) {
                setActivityUtil = new SetActivityUtil();
            }
        }
        return setActivityUtil;
    }

    /**
     * 设置Activity无标题全屏显示
     * @param activity
     */
    public void setFullScreen(Activity activity){
        //设置无标题
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    public void setting(){

    }

}