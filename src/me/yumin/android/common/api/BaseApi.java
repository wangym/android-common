/**
 * 
 */
package me.yumin.android.common.api;

import me.yumin.android.common.domain.enumtype.IApiEnum;
import me.yumin.android.common.etc.CommonConstant;
import me.yumin.android.common.etc.CommonGlobalVar;
import me.yumin.android.common.etc.LogUtil;

/**
 * @author yumin
 * 
 */
public abstract class BaseApi {

	/**
	 * 
	 * @param apiEnum
	 * @return
	 */
	protected static String getApiUrl(IApiEnum apiEnum) {

		String apiUrl = null;

		if (null != apiEnum) {
			if (CommonConstant.V_TEST.equalsIgnoreCase(CommonGlobalVar.mode)) {
				apiUrl = apiEnum.testUrl();
			} else if (CommonConstant.V_PROD.equalsIgnoreCase(CommonGlobalVar.mode)) {
				apiUrl = apiEnum.prodUrl();
			}
		}

		LogUtil.logV("BaseApi.getApiUrl", apiUrl);

		return apiUrl;
	}
}
