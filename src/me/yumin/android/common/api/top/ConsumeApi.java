/**
 * 
 */
package me.yumin.android.common.api.top;

import java.util.HashMap;
import java.util.Map;
import me.yumin.android.common.domain.api.param.ConsumeApiParam;
import me.yumin.android.common.domain.api.result.ConsumeApiResult;
import me.yumin.android.common.etc.CommonConstant;
import me.yumin.android.common.etc.CommonUtil;
import me.yumin.android.common.etc.HttpUtil;
import me.yumin.android.common.etc.LogUtil;
import org.json.JSONObject;

/**
 * @author yumin
 * 
 */
public class ConsumeApi extends BaseTopApi {

	/**
	 * 
	 */
	private static final String TAG = ConsumeApi.class.getSimpleName();

	/**
	 * 
	 * @param apiParam
	 * @return
	 */
	public static ConsumeApiResult request(ConsumeApiParam apiParam) {

		ConsumeApiResult apiResult = null;

		try {
			Map<String, Object> paramMap = createParamMap(apiParam);
			String json = HttpUtil.postParam(API_CONSUME, paramMap);
			LogUtil.logV(TAG, json);
			if (CommonUtil.isNotEmpty(json)) {
				JSONObject rootObject = new JSONObject(json);
				if (null != rootObject) {
					apiResult = new ConsumeApiResult(rootObject);
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
	private static Map<String, Object> createParamMap(ConsumeApiParam apiParam) {

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
