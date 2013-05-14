/**
 * 
 */
package me.yumin.android.common.api.top;

import java.util.HashMap;
import java.util.Map;
import me.yumin.android.common.domain.api.param.AutoLoginV2ApiParam;
import me.yumin.android.common.domain.api.result.AutoLoginV2ApiResult;
import me.yumin.android.common.etc.CommonConstant;
import me.yumin.android.common.etc.CommonUtil;
import me.yumin.android.common.etc.HttpUtil;
import org.json.JSONObject;

/**
 * @author yumin
 * 
 */
public class AutoLoginV2Api extends BaseTopApi {

	/**
	 * 
	 * @param apiParam
	 * @return
	 */
	public static AutoLoginV2ApiResult request(AutoLoginV2ApiParam apiParam) {

		AutoLoginV2ApiResult apiResult = null;

		try {
			Map<String, Object> paramMap = createParamMap(apiParam);
			String json = HttpUtil.postParam(CommonConstant.API_AUTO_LOGIN_V2, paramMap);
			if (CommonUtil.isNotEmpty(json)) {
				JSONObject rootObject = new JSONObject(json);
				if (null != rootObject) {
					apiResult = new AutoLoginV2ApiResult(rootObject);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return apiResult;
	}

	// ====================
	// private methods
	// ====================

	/**
	 * 
	 * @param apiParam
	 * @return
	 */
	private static Map<String, Object> createParamMap(AutoLoginV2ApiParam apiParam) {

		Map<String, Object> paramMap = null;

		if (null != apiParam) {
			// 变量定义及计算
			long t = CommonUtil.getTimestamp();
			String appKey = apiParam.getAppKey();
			String appSecret = apiParam.getAppSecret();
			String username = apiParam.getUsername();
			String topToken = CommonUtil.digestToHex(appKey + 
					CommonUtil.digestToHex(appSecret, CommonConstant.CHARSET_NAME) + username + t, 
					CommonConstant.CHARSET_NAME);
			// 参数子集的生成
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put(CommonConstant.K_APP_KEY, appKey);
			dataMap.put(CommonConstant.K_TOKEN, apiParam.getToken());
			dataMap.put(CommonConstant.K_TOP_TOKEN, topToken);
			JSONObject jsonObject = new JSONObject(dataMap);
			String dataJson = jsonObject.toString();
			// 拼进父集及其它
			paramMap = getParamMap(dataJson, t, apiParam.getTtid());
		}

		return paramMap;
	}
}
