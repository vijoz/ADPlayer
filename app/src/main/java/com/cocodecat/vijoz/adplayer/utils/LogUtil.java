package com.cocodecat.vijoz.adplayer.utils;

import android.content.Context;
import android.util.Log;


import com.cocodecat.vijoz.adplayer.BuildConfig;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 一个简单的日志工具封装，可简单自己定位TAG，TAG的格式为：类名[方法名， 调用行数]
 *
 * @author vijoz
 */
public abstract class LogUtil {

	public static final String TAG = "ADPlayerClient";
	private static final String LOG_FILE = "vijoz.adplayer.client.log";

	public static void init(boolean isDebug) {
		IS_DEBUG = isDebug;
	}

	private static boolean IS_DEBUG = BuildConfig.DEBUG;

	public static boolean allowD = IS_DEBUG;
	public static boolean allowE = IS_DEBUG;
	public static boolean allowI = IS_DEBUG;
	public static boolean allowV = IS_DEBUG;
	public static boolean allowW = IS_DEBUG;
	public static boolean allowWtf = IS_DEBUG;


	private static Context mContext = null;

	public static void openLog(Context context) {
		mContext = context;
	}

	// d方法
	// -----------------------------------------------------------------------------------------------------------------
	public static void d(String content) {
		if (!allowD || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.d(tag, content);
	}

	public static void d(String content, Throwable tr) {
		if (!allowD || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.d(tag, content, tr);
	}

	// e方法
	// ------------------------------------------------------------------------------------------------------------------
	public static void e(String content) {
		if (!allowE || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.e(tag, content);
	}

	public static void e(String content, Throwable tr) {
		if (!allowE || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.e(tag, content, tr);
	}

	// i方法
	// -----------------------------------------------------------------------------------------------------------------
	public static void i(String content) {
		if (!allowI || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.i(tag, content);
	}

	public static void i(String content, Throwable tr) {
		if (!allowI || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.i(tag, content, tr);
	}

	// v方法
	// -----------------------------------------------------------------------------------------------------------------
	public static void v(String content) {
		if (!allowV || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.v(tag, content);
	}

	public static void v(String content, Throwable tr) {
		if (!allowV || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.v(tag, content, tr);
	}

	// w方法
	// -----------------------------------------------------------------------------------------------------------------
	public static void w(String content) {
		if (!allowW || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.w(tag, content);
	}

	public static void w(String content, Throwable tr) {
		if (!allowW || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.w(tag, content, tr);
	}

	public static void w(Throwable tr) {
		if (!allowW) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.w(tag, tr);
	}

	// wtf方法，非常恐怖的错误，这种错误原则上不应该出现在系统中，哈哈
	// -----------------------------------------------------------------------------------------------------------------
	public static void wtf(String content) {
		if (!allowWtf || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.wtf(tag, content);
	}

	public static void wtf(String content, Throwable tr) {
		if (!allowWtf || ValidatorUtil.isEmpty(content)) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.wtf(tag, content, tr);
	}

	public static void wtf(Throwable tr) {
		if (!allowWtf) {
			return;
		}
		StackTraceElement caller = getCallerMethodName();
		String tag = generateTag(caller);
		Log.wtf(tag, tr);
	}

	// 跟踪到调用该日志的方法
	private static StackTraceElement getCallerMethodName() {
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		return stacks[4];
	}

	// 规范TAG格式：类名[方法名， 调用行数]

	/**
	 * 生成Tag
	 *
	 * @param stackTraceElement
	 * @return
	 */
	public static String generateTag(StackTraceElement stackTraceElement) {
		String tag = "[" + TAG + "] %s[%s, %d]";
		String callerClazzName = stackTraceElement.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName
				.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, stackTraceElement.getMethodName(),
				stackTraceElement.getLineNumber());
		return tag;
	}

	/**
	 * 写log到文件中
	 *
	 * @param log
	 * @return
	 */
	public static boolean writeToFile(String log) {

		log = log + "\n";

		boolean res = false;
		try {
			// Properties properties = new Properties();
			FileOutputStream fOut = mContext.openFileOutput(LOG_FILE,
					Context.MODE_APPEND);
			try {
				fOut.write(log.getBytes());
				res = true;
			} catch (IOException e) {
				Log.e(TAG, e.toString());
			}

		} catch (FileNotFoundException e) {
			Log.e(TAG, e.toString());
		}
		return res;
	}

	public static String makeLogTag(Class cls) {
		return "音乐笔记:" + cls.getSimpleName();
	}
}