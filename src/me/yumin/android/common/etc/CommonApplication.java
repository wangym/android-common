/**
 * 
 */
package me.yumin.android.common.etc;

import android.app.Application;
import android.util.Log;

/**
 * @author yumin
 * 
 */
public class CommonApplication extends Application {

	/**
	 * 
	 */
	private final static String TAG = CommonApplication.class.getSimpleName();

	/**
	 * 
	 */
	@Override
	public void onCreate() {

		Log.v(TAG, "onCreate");
		super.onCreate();
		init();
	}

	@Override
	public void onLowMemory() {

		Log.v(TAG, "onLowMemory");
		super.onLowMemory();
		destroy();
	}

	@Override
	public void onTerminate() {

		Log.v(TAG, "onTerminate");
		super.onTerminate();
		destroy();
	}

	// ====================
	// private methods
	// ====================

	/**
	 * 
	 * @return
	 */
	private void init() {

		Log.v(TAG, "init");
		CommonGlobalVar.context = this;
		CommonGlobalVar.config = StorageUtil.loadAssetsProperties(CommonConstant.F_CONFIG_PROPERTIES);
		CommonGlobalVar.mode = CommonUtil.getPropertyValue(CommonGlobalVar.config, CommonConstant.K_MODE);
		CommonGlobalVar.imei = SystemUtil.getImei();
		CommonGlobalVar.imsi = SystemUtil.getImsi();
		CrashHandler.getInstance().init();
	}

	/**
	 * 
	 * @return
	 */
	private void destroy() {

		Log.v(TAG, "destroy");
	}
}
