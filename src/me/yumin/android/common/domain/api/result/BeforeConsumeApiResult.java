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
public class BeforeConsumeApiResult extends BaseApiResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1264552540774331876L;

	/**
	 * 
	 */
	private String buyerNick;
	private String codeLeftNum;
	private String itemTitle;
	private String sellerNick;
	private String itemPicUrl;

	/**
	 * 
	 */
	private String code;
	private String mobile;

	/**
	 * 
	 * @param rootObject
	 */
	public BeforeConsumeApiResult(JSONObject rootObject) {

		jsonToParentAttribute(rootObject);
		jsonToChildAttribute(rootObject);
	}

	@Override
	public void jsonToChildAttribute(JSONObject rootObject) {

		try {
			if (null != rootObject) {
				JSONObject dataObject = rootObject.getJSONObject(CommonConstant.K_DATA);
				if (null != dataObject) {
					setBuyerNick(dataObject.isNull(CommonConstant.K_BUYER_NICK) ? null : dataObject.getString(CommonConstant.K_BUYER_NICK));
					setCodeLeftNum(dataObject.isNull(CommonConstant.K_CODE_LEFT_NUM) ? null : dataObject.getString(CommonConstant.K_CODE_LEFT_NUM));
					setItemTitle(dataObject.isNull(CommonConstant.K_ITEM_TITLE) ? null : dataObject.getString(CommonConstant.K_ITEM_TITLE));
					setSellerNick(dataObject.isNull(CommonConstant.K_SELLER_NICK) ? null : dataObject.getString(CommonConstant.K_SELLER_NICK));
					setItemPicUrl(dataObject.isNull(CommonConstant.K_ITEM_PIC_URL) ? null : dataObject.getString(CommonConstant.K_ITEM_PIC_URL));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getCodeLeftNum() {
		return codeLeftNum;
	}

	public void setCodeLeftNum(String codeLeftNum) {
		this.codeLeftNum = codeLeftNum;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getItemPicUrl() {
		return itemPicUrl;
	}

	public void setItemPicUrl(String itemPicUrl) {
		this.itemPicUrl = itemPicUrl;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
