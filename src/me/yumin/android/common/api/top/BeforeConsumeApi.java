/**
 * 
 */
package me.yumin.android.common.api.top;

import java.util.HashMap;
import java.util.Map;
import me.yumin.android.common.domain.api.param.BeforeConsumeApiParam;
import me.yumin.android.common.domain.api.result.BeforeConsumeApiResult;
import me.yumin.android.common.etc.CommonConstant;
import me.yumin.android.common.etc.CommonUtil;
import me.yumin.android.common.etc.HttpUtil;
import org.json.JSONObject;

/**
 * @author yumin
 * 
 */
public class BeforeConsumeApi extends BaseTopApi {

	/**
	 * 
	 * @param apiParam
	 * @return
	 */
	public static BeforeConsumeApiResult request(BeforeConsumeApiParam apiParam) {

		BeforeConsumeApiResult apiResult = null;

		try {
			Map<String, Object> paramMap = createParamMap(apiParam);
			String json = HttpUtil.postParam(CommonConstant.API_BEFORE_CONSUME, paramMap);
			if (CommonUtil.isNotEmpty(json)) {
				JSONObject rootObject = new JSONObject(json);
				if (null != rootObject) {
					apiResult = new BeforeConsumeApiResult(rootObject);
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
	private static Map<String, Object> createParamMap(BeforeConsumeApiParam apiParam) {

		Map<String, Object> paramMap = null;

		if (null != apiParam) {
			// 变量定义及计算
			long t = CommonUtil.getTimestamp();
			// 参数子集的生成
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put(CommonConstant.K_APP_KEY, apiParam.getAppKey());
			dataMap.put(CommonConstant.K_ETICKET_TOKEN, apiParam.getEticketToken());
			dataMap.put(CommonConstant.K_SID, apiParam.getSid());
			JSONObject jsonObject = new JSONObject(dataMap);
			String dataJson = jsonObject.toString();
			// 拼进父集及其它
			paramMap = getParamMap(dataJson, t, apiParam.getTtid());
		}

		return paramMap;
	}
}
