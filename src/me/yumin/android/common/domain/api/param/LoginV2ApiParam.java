/**
 * 
 */
package me.yumin.android.common.domain.api.param;

import java.io.Serializable;

/**
 * @author yumin
 * 
 */
public class LoginV2ApiParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4414008407947050158L;

	/**
	 * 
	 */
	private String appKey;
	private String appSecret;
	private String checkCode;
	private String checkCodeId;
	private String rsaPassword;
	private String token;
	private String ttid;
	private String username;

	/**
	 * 
	 */
	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getCheckCodeId() {
		return checkCodeId;
	}

	public void setCheckCodeId(String checkCodeId) {
		this.checkCodeId = checkCodeId;
	}

	public String getRsaPassword() {
		return rsaPassword;
	}

	public void setRsaPassword(String rsaPassword) {
		this.rsaPassword = rsaPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTtid() {
		return ttid;
	}

	public void setTtid(String ttid) {
		this.ttid = ttid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
