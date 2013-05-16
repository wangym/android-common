/**
 * 
 */
package me.yumin.android.common.etc;

import android.app.Application;

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

		LogUtil.logV(TAG, "onCreate");
		super.onCreate();
		init();
	}

	@Override
	public void onLowMemory() {

		LogUtil.logV(TAG, "onLowMemory");
		super.onLowMemory();
		destroy();
	}

	@Override
	public void onTerminate() {

		LogUtil.logV(TAG, "onTerminate");
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

		LogUtil.logV(TAG, "init");
		CommonGlobalVar.context = this;
		CommonGlobalVar.config = StorageUtil.loadAssetsProperties(CommonConstant.F_CONFIG_PROPERTIES);
		CommonGlobalVar.log = CommonUtil.getPropertyValue(CommonGlobalVar.config, CommonConstant.K_LOG);
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

		LogUtil.logV(TAG, "destroy");
	}
}
