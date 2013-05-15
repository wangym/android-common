/**
 * 
 */
package me.yumin.android.common.etc;

import android.widget.Toast;

/**
 * @author yumin
 * 
 */
public final class ViewUtil {

	/**
	 * 通过浮出层来显示提示
	 * 
	 * @param text 任意提示字符串
	 */
	public static void showToast(String text) {

		if (null != CommonGlobalVar.context && CommonUtil.isNotEmpty(text)) {
			Toast.makeText(CommonGlobalVar.context, text, Toast.LENGTH_SHORT).show();
		}
	}
}
