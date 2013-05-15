/**
 * 
 */
package me.yumin.android.common.etc;

import java.io.InputStream;
import java.util.Properties;
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

		SharedPreferences sharedPreferences = CommonGlobalVar.context.getSharedPreferences(name, Context.MODE_PRIVATE);

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

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties loadAssetsProperties(String fileName) {

		Properties properties = null;

		if (CommonUtil.isNotEmpty(fileName)) {
			try {
				InputStream stream = CommonGlobalVar.context.getAssets().open(fileName);
				if (null != stream && stream.markSupported()) {
					properties = new Properties();
					properties.load(stream);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return properties;
	}
}
