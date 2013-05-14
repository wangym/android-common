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
	public static void logV(String mode, String tag, String msg) {

		if (CommonUtil.isNotEmpty(mode) && "debug".equalsIgnoreCase(mode)) {
			Log.v(tag, CommonUtil.isNotEmpty(msg) ? msg : "");
		}
	}
}
