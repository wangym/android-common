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
	 * @param tag
	 * @param msg
	 */
	public static void logV(String tag, String msg) {

		if (CommonConstant.V_ENABLE.equalsIgnoreCase(CommonGlobalVar.log)) {
			Log.v(tag, CommonUtil.isNotEmpty(msg) ? msg : "");
		}
	}
}
