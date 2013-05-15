/**
 * 
 */
package me.yumin.android.common.api.top;

import java.util.HashMap;
import java.util.Map;
import me.yumin.android.common.etc.CommonConstant;
import me.yumin.android.common.etc.SystemUtil;

/**
 * @author yumin
 * 
 */
public abstract class BaseTopApi {

	// 接口
	protected static final String API_AUTO_LOGIN_V2 = "http://api.m.taobao.com/rest/api2.do?api=com.taobao.client.sys.autoLogin&v=v2";
	protected static final String API_GET_APP_TOKEN = "http://api.m.taobao.com/rest/api2.do?api=com.taobao.client.sys.getAppToken&v=*";
	protected static final String API_LOGIN_V2 = "http://api.m.taobao.com/rest/api2.do?api=com.taobao.client.sys.login&v=v2";
	protected static final String API_BEFORE_CONSUME = "http://api.m.taobao.com/rest/api2.do?api=mtop.eticket.wirelessbeforeconsume&v=1.0";
	protected static final String API_CONSUME = "http://api.m.taobao.com/rest/api2.do?api=mtop.eticket.wirelessConsume&v=1.0";

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
