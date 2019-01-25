package com.cocodecat.vijoz.adplayer.base;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.cocodecat.vijoz.adplayer.R;
import com.cocodecat.vijoz.adplayer.activity.MainActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application {
    private static Gson sGson;
    public boolean DEBUG = true;
    private static MyApplication myApplication;//每次创建时不重复创建该对象,必须声明为静态对象

    private static final Object LockThis = new Object();

    private Context mContext;
    public static int H,W;

    public synchronized static MyApplication getInstance() {
        if (null == myApplication) {
            synchronized (LockThis) {
                if (null == myApplication) {
                    myApplication = new MyApplication();
                }
            }
        }
        return myApplication;
    }

    public static Gson getGson() {
        if (sGson==null){
            sGson=new Gson();
        }
        return sGson;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        getScreen(this);
        Fresco.initialize(this);
//        Recovery.getInstance()
//                .debug(true)
//                .recoverInBackground(false)
//                .recoverStack(true)
//                .mainPage(MainActivity.class)
//                .init(this);
//        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5b1534cf");

    }

    public void getScreen(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        H=dm.heightPixels;
        W=dm.widthPixels;
    }

    public Context getAPPContext(){
        return mContext;
    }

}