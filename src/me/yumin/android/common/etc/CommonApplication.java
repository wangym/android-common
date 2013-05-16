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
	public static Application context;

	/**
	 * 
	 */
	@Override
	public void onCreate() {

		super.onCreate();
		init();
	}

	@Override
	public void onLowMemory() {

		super.onLowMemory();
		destroy();
	}

	@Override
	public void onTerminate() {

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

		context = this;

		CommonGlobalVar.config = StorageUtil.loadAssetsProperties(CommonConstant.F_CONFIG_PROPERTIES);
		CommonGlobalVar.log = CommonUtil.getPropertyValue(CommonGlobalVar.config, CommonConstant.K_LOG, CommonConstant.V_DISABLE);
		CommonGlobalVar.mode = CommonUtil.getPropertyValue(CommonGlobalVar.config, CommonConstant.K_MODE, CommonConstant.V_PROD);
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
