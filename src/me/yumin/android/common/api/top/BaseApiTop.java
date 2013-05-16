/**
 * 
 */
package me.yumin.android.common.api.top;

import java.util.HashMap;
import java.util.Map;
import me.yumin.android.common.api.BaseApi;
import me.yumin.android.common.etc.CommonConstant;
import me.yumin.android.common.etc.SystemUtil;

/**
 * @author yumin
 * 
 */
public abstract class BaseApiTop extends BaseApi {

	/**
	 * 
	 * @param json
	 * @param t
	 * @param ttid
	 * @return
	 */
	protected static Map<String, Object> getParamMap(String json, long t, String ttid) {

		Map<String, Object> paramMap = null;

		// 拼进父集及其它
		paramMap = new HashMap<String, Object>();
		paramMap.put(CommonConstant.K_DATA, json);
		paramMap.put(CommonConstant.K_IMEI, SystemUtil.getImei());
		paramMap.put(CommonConstant.K_IMSI, SystemUtil.getImsi());
		paramMap.put(CommonConstant.K_T, t);
		paramMap.put(CommonConstant.K_TTID, ttid);

		return paramMap;
	}
}
