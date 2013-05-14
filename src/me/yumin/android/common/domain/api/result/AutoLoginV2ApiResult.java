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
public class AutoLoginV2ApiResult extends BaseApiResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1264552540774331876L;

	/**
	 * 
	 */
	private String ecode;
	private String logintime;
	private String sid;
	private String userId;
	private String username;

	/**
	 * 
	 * @param rootObject
	 */
	public AutoLoginV2ApiResult(JSONObject rootObject) {

		jsonToParentAttribute(rootObject);
		jsonToChildAttribute(rootObject);
	}

	@Override
	public void jsonToChildAttribute(JSONObject rootObject) {

		try {
			if (null != rootObject) {
				JSONObject dataObject = rootObject.getJSONObject(CommonConstant.K_DATA);
				if (null != dataObject) {
					setEcode(dataObject.isNull(CommonConstant.K_ECODE) ? null : dataObject.getString(CommonConstant.K_ECODE));
					setLogintime(dataObject.isNull(CommonConstant.K_LOGIN_TIME) ? null : dataObject.getString(CommonConstant.K_LOGIN_TIME));
					setSid(dataObject.isNull(CommonConstant.K_SID) ? null : dataObject.getString(CommonConstant.K_SID));
					setUserId(dataObject.isNull(CommonConstant.K_USER_ID) ? null : dataObject.getString(CommonConstant.K_USER_ID));
					setUsername(dataObject.isNull(CommonConstant.K_NICK) ? null : dataObject.getString(CommonConstant.K_NICK));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public String getEcode() {
		return ecode;
	}

	public void setEcode(String ecode) {
		this.ecode = ecode;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
