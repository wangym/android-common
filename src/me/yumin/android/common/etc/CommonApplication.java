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
	public static Application context;

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
		context = this;
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
