/**
 * 
 */
package me.yumin.android.common.domain.enumtype.impl;

import me.yumin.android.common.domain.enumtype.IApiEnum;

/**
 * @author yumin
 * 
 */
public enum ApiTopEnum implements IApiEnum {

	/**
	 * 
	 */
	AUTO_LOGIN_V2("http://api.m.taobao.com/rest/api2.do?api=com.taobao.client.sys.autoLogin&v=v2", ""),
	GET_APP_TOKEN("http://api.m.taobao.com/rest/api2.do?api=com.taobao.client.sys.getAppToken&v=*",	""),
	LOGIN_V2("http://api.m.taobao.com/rest/api2.do?api=com.taobao.client.sys.login&v=v2", ""),
	BEFORE_CONSUME("http://api.m.taobao.com/rest/api2.do?api=mtop.eticket.wirelessbeforeconsume&v=1.0", ""),
	CONSUME("http://api.m.taobao.com/rest/api2.do?api=mtop.eticket.wirelessConsume&v=1.0", "");

	/**
     * 
     */
	private String prodUrl;
	private String testUrl;

	/**
	 * 
	 * @param prodUrl
	 * @param testUrl
	 */
	ApiTopEnum(String prodUrl, String testUrl) {

		this.prodUrl = prodUrl;
		this.testUrl = testUrl;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String prodUrl() {
		return this.prodUrl;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String testUrl() {
		return this.testUrl;
	}
}
