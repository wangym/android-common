/**
 * 
 */
package me.yumin.android.common.domain.api.result;

import java.io.Serializable;
import org.json.JSONObject;

/**
 * @author yumin
 * 
 */
public class ConsumeApiResult extends BaseApiResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2742506498093647056L;

	/**
	 * 
	 */
	private String consumeNum;
	private String eticketToken;

	/**
	 * 
	 * @param rootObject
	 */
	public ConsumeApiResult(JSONObject rootObject) {

		jsonToParentAttribute(rootObject);
		jsonToChildAttribute(rootObject);
	}

	@Override
	public void jsonToChildAttribute(JSONObject rootObject) {

	}

	/**
	 * 
	 */
	public String getConsumeNum() {
		return consumeNum;
	}

	public void setConsumeNum(String consumeNum) {
		this.consumeNum = consumeNum;
	}

	public String getEticketToken() {
		return eticketToken;
	}

	public void setEticketToken(String eticketToken) {
		this.eticketToken = eticketToken;
	}
}
