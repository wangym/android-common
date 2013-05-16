/**
 * 
 */
package me.yumin.android.common.etc;

import java.util.Properties;
import android.app.Application;
import android.util.Log;

/**
 * @author yumin
 */
public abstract class CommonGlobalVar {

	/**
	 * 
	 */
	private static final String TAG = CommonGlobalVar.class.getSimpleName();

	// 全局的配置
	public static Properties config;
	// 应用上下文
	public static Application context;
	// 输出日志否
	public static String log = "";
	// 运行的环境
	public static String mode = "";
	// SIM序列号
	public static String imsi = "";
	// 手机序列号
	public static String imei = "";

	/**
	 * 
	 */
	public static void dumpET() {

		Log.v(TAG, String.format("config=%s", config));
		Log.v(TAG, String.format("context=%s", context));
		Log.v(TAG, String.format("log=%s", log));
		Log.v(TAG, String.format("mode=%s", mode));
		Log.v(TAG, String.format("imsi=%s", imsi));
		Log.v(TAG, String.format("imei=%s", imei));
	}
}
