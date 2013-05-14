/**
 * 
 */
package me.yumin.android.common.domain.api.param;

import java.io.Serializable;

/**
 * @author yumin
 * 
 */
public class GetAppTokenApiParam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4316113825206598326L;

	/**
	 * 
	 */
	private String ttid;
	private String username;

	/**
	 * 
	 */
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
