/**
 * 
 */
package me.yumin.android.common.domain.api.result;

import java.io.Serializable;
import me.yumin.android.common.etc.CommonConstant;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author yumin
 * 
 */
public abstract class BaseApiResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1141372176688505910L;

	/**
	 * 
	 */
	private String flag = "";
	private boolean isSuccess = false;
	private String prompt = "";
	private String retJson = "";

	/**
	 * 
	 * @param rootObject
	 */
	protected void jsonToParentAttribute(JSONObject rootObject) {

		try {
			if (null != rootObject) {
				JSONArray jsonArray = rootObject.getJSONArray(CommonConstant.K_RET);
				if (null != jsonArray) {
					setRetJson(jsonArray.toString());
					String retString = String.valueOf(1 < jsonArray.length() ? jsonArray.get(1) : jsonArray.get(0));
					if (null != retString && retString.contains(CommonConstant.SEPARATE_DOUBLE_COLON)) {
						String[] retArray = retString.split(CommonConstant.SEPARATE_DOUBLE_COLON);
						if (null != retArray && 0 < retArray.length) {
							setFlag(retArray[0]);
							setPrompt(retArray[1]);
							setSuccess(CommonConstant.V_SUCCESS.equalsIgnoreCase(retArray[0]) ? true : false);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param rootObject
	 */
	abstract public void jsonToChildAttribute(JSONObject rootObject);

	/**
	 * 
	 */
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getPrompt() {
		return prompt;
	}

	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}

	public String getRetJson() {
		return retJson;
	}

	public void setRetJson(String retJson) {
		this.retJson = retJson;
	}
}
