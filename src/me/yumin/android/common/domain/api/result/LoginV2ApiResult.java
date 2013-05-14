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
public class LoginV2ApiResult extends BaseApiResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7661186248522656157L;

	/**
	 * 
	 */
	private String checkCodeId;
	private String checkCodeUrl;
	private String ecode;
	private String logintime;
	private String sid;
	private String time;
	private String token;
	private String topSession;
	private String username;
	private String userId;

	/**
	 * 
	 * @param rootObject
	 */
	public LoginV2ApiResult(JSONObject rootObject) {

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
					setCheckCodeId(dataObject.isNull(CommonConstant.K_CHECK_CODE_ID) ? null : dataObject.getString(CommonConstant.K_CHECK_CODE_ID));
					setCheckCodeUrl(dataObject.isNull(CommonConstant.K_CHECK_CODE_URL) ? null : dataObject.getString(CommonConstant.K_CHECK_CODE_URL));
					setEcode(dataObject.isNull(CommonConstant.K_ECODE) ? null : dataObject.getString(CommonConstant.K_ECODE));
					setLogintime(dataObject.isNull(CommonConstant.K_LOGIN_TIME) ? null : dataObject.getString(CommonConstant.K_LOGIN_TIME));
					setSid(dataObject.isNull(CommonConstant.K_SID) ? null : dataObject.getString(CommonConstant.K_SID));
					setTime(dataObject.isNull(CommonConstant.K_TIME) ? null : dataObject.getString(CommonConstant.K_TIME));
					setToken(dataObject.isNull(CommonConstant.K_TOKEN) ? null : dataObject.getString(CommonConstant.K_TOKEN));
					setTopSession(dataObject.isNull(CommonConstant.K_TOP_SESSION) ? null : dataObject.getString(CommonConstant.K_TOP_SESSION));
					setUsername(dataObject.isNull(CommonConstant.K_NICK) ? null : dataObject.getString(CommonConstant.K_NICK));
					setUserId(dataObject.isNull(CommonConstant.K_USER_ID) ? null : dataObject.getString(CommonConstant.K_USER_ID));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public String getCheckCodeId() {
		return checkCodeId;
	}

	public void setCheckCodeId(String checkCodeId) {
		this.checkCodeId = checkCodeId;
	}

	public String getCheckCodeUrl() {
		return checkCodeUrl;
	}

	public void setCheckCodeUrl(String checkCodeUrl) {
		this.checkCodeUrl = checkCodeUrl;
	}

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTopSession() {
		return topSession;
	}

	public void setTopSession(String topSession) {
		this.topSession = topSession;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
