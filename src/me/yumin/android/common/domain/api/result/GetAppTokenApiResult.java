/**
 * 
 */
package me.yumin.android.common.domain.api.result;

import java.io.Serializable;
import me.yumin.android.common.etc.CommonConstant;
import org.json.JSONObject;

/**
 * @author yumin
 * 
 */
public class GetAppTokenApiResult extends BaseApiResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 402276438657119434L;

	/**
	 * 
	 */
	private String pubKey;
	private String token;

	/**
	 * 
	 * @param rootObject
	 */
	public GetAppTokenApiResult(JSONObject rootObject) {

		jsonToParentAttribute(rootObject);
		jsonToChildAttribute(rootObject);
	}

	/**
	 * 
	 * @param rootObject
	 */
	@Override
	public void jsonToChildAttribute(JSONObject rootObject) {

		try {
			if (null != rootObject) {
				JSONObject dataObject = rootObject.getJSONObject(CommonConstant.K_DATA);
				if (null != dataObject) {
					setPubKey(dataObject.isNull(CommonConstant.K_PUB_KEY) ? null : dataObject.getString(CommonConstant.K_PUB_KEY));
					setToken(dataObject.isNull(CommonConstant.K_TOKEN) ? null : dataObject.getString(CommonConstant.K_TOKEN));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public String getPubKey() {
		return pubKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
