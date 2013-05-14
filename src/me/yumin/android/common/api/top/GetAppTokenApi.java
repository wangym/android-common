/**
 * 
 */
package me.yumin.android.common.api.top;

import java.util.HashMap;
import java.util.Map;
import me.yumin.android.common.domain.api.param.GetAppTokenApiParam;
import me.yumin.android.common.domain.api.result.GetAppTokenApiResult;
import me.yumin.android.common.etc.CommonConstant;
import me.yumin.android.common.etc.CommonUtil;
import me.yumin.android.common.etc.HttpUtil;
import org.json.JSONObject;

/**
 * @author yumin
 * 
 */
public class GetAppTokenApi extends BaseTopApi {

	/**
	 * 
	 * @param apiParam
	 * @return
	 */
	public static GetAppTokenApiResult request(GetAppTokenApiParam apiParam) {

		GetAppTokenApiResult apiResult = null;

		try {
			Map<String, Object> paramMap = createParamMap(apiParam);
			String json = HttpUtil.postParam(CommonConstant.API_GET_APP_TOKEN, paramMap);
			if (CommonUtil.isNotEmpty(json)) {
				JSONObject rootObject = new JSONObject(json);
				if (null != rootObject) {
					apiResult = new GetAppTokenApiResult(rootObject);
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
	private static Map<String, Object> createParamMap(GetAppTokenApiParam apiParam) {

		Map<String, Object> paramMap = null;

		if (null != apiParam) {
			// 变量定义及计算
			long t = CommonUtil.getTimestamp();
			String username = apiParam.getUsername();
			if (CommonUtil.isNotEmpty(username)) {
				// 参数子集的生成
				Map<String, String> dataMap = new HashMap<String, String>();
				dataMap.put(CommonConstant.K_KEY, username);
				JSONObject jsonObject = new JSONObject(dataMap);
				String dataJson = jsonObject.toString();
				// 拼进父集及其它
				paramMap = getParamMap(dataJson, t, apiParam.getTtid());
			}
		}

		return paramMap;
	}
}
