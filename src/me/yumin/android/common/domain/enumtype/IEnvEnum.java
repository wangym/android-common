/**
 * 
 */
package me.yumin.android.common.domain.enumtype;

/**
 * @author yumin
 * 
 */
public interface IEnvEnum {

	/**
	 * 生产环境
	 * 
	 * @return
	 */
	public String prodValue();

	/**
	 * 测试环境
	 * 
	 * @return
	 */
	public String testValue();
}
