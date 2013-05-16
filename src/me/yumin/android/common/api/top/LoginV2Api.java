/**
 * 
 */
package me.yumin.android.common.api.top;

import java.util.HashMap;
import java.util.Map;
import me.yumin.android.common.domain.api.param.LoginV2ApiParam;
import me.yumin.android.common.domain.api.result.LoginV2ApiResult;
import me.yumin.android.common.domain.enumtype.impl.ApiEnum;
import me.yumin.android.common.etc.CommonConstant;
import me.yumin.android.common.etc.CommonUtil;
import me.yumin.android.common.etc.HttpUtil;
import me.yumin.android.common.etc.LogUtil;
import org.json.JSONObject;

/**
 * @author yumin
 * 
 */
public class LoginV2Api extends BaseApiTop {

	/**
	 * 
	 * @param apiParam
	 * @return
	 */
	public static LoginV2ApiResult request(LoginV2ApiParam apiParam) {

		LoginV2ApiResult apiResult = null;

		try {
			Map<String, Object> paramMap = createParamMap(apiParam);
			String json = HttpUtil.postParam(CommonUtil.getEnvValue(ApiEnum.TOP_LOGIN_V2, null), paramMap);
			LogUtil.logV("LoginV2Api.request", json);
			if (CommonUtil.isNotEmpty(json)) {
				JSONObject rootObject = new JSONObject(json);
				if (null != rootObject) {
					apiResult = new LoginV2ApiResult(rootObject);
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
	private static Map<String, Object> createParamMap(LoginV2ApiParam apiParam) {

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
			dataMap.put(CommonConstant.K_PASSWORD, apiParam.getRsaPassword());
			dataMap.put(CommonConstant.K_TOKEN, apiParam.getToken());
			dataMap.put(CommonConstant.K_TOP_TOKEN, topToken);
			dataMap.put(CommonConstant.K_USERNAME, username);
			JSONObject jsonObject = new JSONObject(dataMap);
			String dataJson = jsonObject.toString();
			// 拼进父集及其它
			paramMap = getParamMap(dataJson, t, apiParam.getTtid());
		}

		return paramMap;
	}
}
