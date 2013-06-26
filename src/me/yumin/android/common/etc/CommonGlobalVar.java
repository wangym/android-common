/**
 * 
 */
package me.yumin.android.common.etc;

import java.util.Properties;
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
	// 输出日志否
	public static String log = "";
	// 运行的环境
	public static String mode = "";
	// 手机序列号
	public static String imei = "";
	// SIM序列号
	public static String imsi = "";

	/**
	 * 
	 */
	public static void dumpET() {

		Log.v(TAG, String.format("config=%s", config));
		Log.v(TAG, String.format("log=%s", log));
		Log.v(TAG, String.format("mode=%s", mode));
		Log.v(TAG, String.format("imei=%s", imei));
		Log.v(TAG, String.format("imsi=%s", imsi));
	}
}
