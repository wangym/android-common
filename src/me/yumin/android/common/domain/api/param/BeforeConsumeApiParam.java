/**
 * 
 */
package me.yumin.android.common.domain.api.param;

import java.io.Serializable;

/**
 * @author yumin
 * 
 */
public class BeforeConsumeApiParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5678927542179189189L;

	/**
	 * 
	 */
	private String appKey;
	private String eticketToken;
	private String sid;
	private String ttid;

	/**
	 * 
	 */
	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getEticketToken() {
		return eticketToken;
	}

	public void setEticketToken(String eticketToken) {
		this.eticketToken = eticketToken;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getTtid() {
		return ttid;
	}

	public void setTtid(String ttid) {
		this.ttid = ttid;
	}
}
