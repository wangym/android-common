/**
 * 
 */
package me.yumin.android.common.etc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author yumin
 * 
 */
public final class NetworkUtil {

	/**
	 * 判断网络连接是否可用
	 * 
	 * @return true=网络连接可用|false=不可用
	 */
	public static boolean isNetworkConnected() {

		boolean result = false;

		if (null != CommonGlobalVar.context) {
			ConnectivityManager connMgr = (ConnectivityManager) CommonGlobalVar.context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (null != connMgr) {
				NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				if (null != networkInfo && networkInfo.isConnected()) {
					return true;
				}
			}
		}

		return result;
	}
}
