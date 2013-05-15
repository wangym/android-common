/**
 * 
 */
package me.yumin.android.common.etc;

import android.util.Log;

/**
 * @author yumin
 * 
 */
public class LogUtil {

	/**
	 * 
	 * @param mode
	 * @param tag
	 * @param msg
	 */
	public static void logV(String tag, String msg) {

		if ("debug".equalsIgnoreCase(CommonGlobalVar.mode)) {
			Log.v(tag, CommonUtil.isNotEmpty(msg) ? msg : "");
		}
	}
}
