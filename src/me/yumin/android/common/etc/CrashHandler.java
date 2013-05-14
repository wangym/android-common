/**
 * 
 */
package me.yumin.android.common.etc;

import java.lang.Thread.UncaughtExceptionHandler;
import android.os.Debug;
import android.os.Environment;

/**
 * @author yumin
 * 
 */
public class CrashHandler implements UncaughtExceptionHandler {

	/**
	 * 
	 */
	private static final String PATH_DATA_HPROF = Environment.getExternalStorageDirectory().getPath() + "/data.hprof";
	private static final String OUT_OF_MEM_ERROR = "java.lang.OutOfMemoryError";

	/**
	 * 
	 */
	private static CrashHandler crashHandler;
	private Thread.UncaughtExceptionHandler defaultHandler;

	@Override
	public void uncaughtException(Thread thread, Throwable throwable) {

		if (isOutOfMemoryError(throwable)) {
			try {
				Debug.dumpHprofData(PATH_DATA_HPROF);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (null != defaultHandler) {
			defaultHandler.uncaughtException(thread, throwable);
		} else {
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}
	}

	/**
	 * 
	 */
	private CrashHandler() {
	}

	/**
	 * 
	 */
	public void init() {

		defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 
	 * @return
	 */
	public static synchronized CrashHandler getInstance() {

		if (null == crashHandler) {
			crashHandler = new CrashHandler();
		}

		return crashHandler;
	}

	/**
	 * 
	 * @param throwable
	 * @return
	 */
	private static boolean isOutOfMemoryError(Throwable throwable) {

		boolean result = false;

		String name = throwable.getClass().getName();
		if (OUT_OF_MEM_ERROR.equals(name)) {
			result = true;
		} else {
			Throwable cause = throwable.getCause();
			if (null != cause) {
				result = isOutOfMemoryError(cause);
			} else {
				result = false;
			}
		}

		return result;
	}
}
