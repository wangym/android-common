/**
 * 
 */
package me.yumin.android.common.etc;

import me.yumin.android.common.thirdparty.PhoneInfo;

/**
 * @author yumin
 * 
 */
public final class SystemUtil {

	/**
	 * 杀掉进程退出
	 */
	public static void exit() {

		int pid = android.os.Process.myPid();
		android.os.Process.killProcess(pid);
	}

	/**
	 * 
	 * @return
	 */
	public static String getImsi() {

		if (!CommonUtil.isNotEmpty(CommonGlobalVar.imsi) && null != CommonApplication.context) {
			CommonGlobalVar.imsi = PhoneInfo.getImsi(CommonApplication.context);
		}

		return CommonGlobalVar.imsi;
	}

	/**
	 * 
	 * @return
	 */
	public static String getImei() {

		if (!CommonUtil.isNotEmpty(CommonGlobalVar.imei) && null != CommonApplication.context) {
			CommonGlobalVar.imei = PhoneInfo.getImei(CommonApplication.context);
		}

		return CommonGlobalVar.imei;
	}
}
