/**
 * 
 */
package me.yumin.android.common.etc;

import android.util.Log;

/**
 * @author yumin
 */
public abstract class CommonGlobalVar {

	/**
	 * 
	 */
	private static final String TAG = CommonGlobalVar.class.getSimpleName();

	// SIM序列号
	public static String imsi = "";
	// 手机序列号
	public static String imei = "";

	/**
	 * 
	 */
	public static void dumpET() {

		Log.v(TAG, String.format("imsi=%s", imsi));
		Log.v(TAG, String.format("imei=%s", imei));
	}
}
