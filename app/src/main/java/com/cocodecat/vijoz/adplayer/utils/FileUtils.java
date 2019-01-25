package com.cocodecat.vijoz.adplayer.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;


import com.cocodecat.vijoz.adplayer.base.MyApplication;

import java.io.File;

public class FileUtils {
    private static final String APP_ROOT_DICT = "/ADPlayer";//本地缓存文件夹路径
    private static final String AFiles = "/Afiles/";
    private static final String BFiles = "/Bfiles/";

    public static String AFilesPath(Context context) {
        String path = getFilePath(MyApplication.getInstance().getAPPContext()) + AFiles;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);//扫描单个文件
            intent.setData(Uri.fromFile(file));//给图片的绝对路径
            context.sendBroadcast(intent);
        }
        return path;
    }

    public static String BFilesPath(Context context) {
        String path = getFilePath(MyApplication.getInstance().getAPPContext()) + BFiles;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);//扫描单个文件
            intent.setData(Uri.fromFile(file));//给图片的绝对路径
            context.sendBroadcast(intent);
        }
        return path;
    }

    /**
     * 获取app文件夹路径
     *
     * @param context
     * @return
     */
    private static String getFilePath(Context context) {
        String path;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {// 优先保存到SD卡中
            path = Environment.getExternalStorageDirectory().getAbsolutePath() + APP_ROOT_DICT;
        } else {// 如果SD卡不存在，就保存到本应用的目录下
            path = context.getFilesDir().getAbsolutePath();
        }
        return path;
    }
}
