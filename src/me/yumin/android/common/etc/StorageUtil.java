/**
 * 
 */
package me.yumin.android.common.etc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

/**
 * @author yumin
 * 
 */
public final class StorageUtil {

	/**
	 * 
	 * @param name 文件名前缀
	 * @return
	 */
	public static SharedPreferences getSharedPreferences(String name) {

		SharedPreferences sharedPreferences = CommonApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);

		return sharedPreferences;
	}

	/**
	 * 判断是否已加载扩展存储
	 * 
	 * @return true=已加载扩展存储|false=未加载
	 */
	public static boolean isMediaMounted() {

		boolean result = false;

		String storageState = Environment.getExternalStorageState();
		if (CommonUtil.isNotEmpty(storageState)) {
			result = storageState.equals(android.os.Environment.MEDIA_MOUNTED);
		}

		return result;
	}
}
