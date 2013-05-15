/**
 * 
 */
package me.yumin.android.common.etc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.yumin.android.common.thirdparty.Base64;

/**
 * @author yumin
 * 
 */
public final class CommonUtil {

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public static String byteToHex(byte[] bytes) {

		String string = null;

		String tmp = "";
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			tmp = (java.lang.Integer.toHexString(bytes[i] & 0XFF));
			if (1 == tmp.length()) {
				stringBuffer.append("0").append(tmp);
			} else {
				stringBuffer.append(tmp);
			}
		}
		string = stringBuffer.toString().toUpperCase();

		return string;
	}

	/**
	 * 
	 * @param string
	 * @param charsetName
	 * @return
	 */
	public static String digestToHex(String string, String charsetName) {

		String result = null;

		if (null != string) {
			try {
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				result = byteToHex(messageDigest.digest(string.getBytes(charsetName)));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result.toLowerCase();
	}

	/**
	 * 
	 * @param base64Bytes
	 * @return
	 */
	public static Object getBase64InputStream(byte[] base64Bytes) {

		Object object = null;

		if (null != base64Bytes && 0 < base64Bytes.length) {
			try {
				ByteArrayInputStream bytesInputStream = new ByteArrayInputStream(Base64.decodeBase64(base64Bytes));
				ObjectInputStream objectInputStream = new ObjectInputStream(bytesInputStream);
				object = objectInputStream.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return object;
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] getBase64OutputStream(Object object) {

		byte[] base64Bytes = null;

		if (null != object) {
			try {
				ByteArrayOutputStream bytesOutputStream = new ByteArrayOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(bytesOutputStream);
				objectOutputStream.writeObject(object);
				base64Bytes = Base64.encodeBase64(bytesOutputStream.toByteArray());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return base64Bytes;
	}

	/**
	 * 
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getPropertyValue(Properties properties, String key) {

		String value = null;

		if (null != properties && isNotEmpty(key)) {
			if (properties.containsKey(key)) {
				value = properties.getProperty(key);
			}
		}

		return value;
	}

	/**
	 * 
	 * @return
	 */
	public static long getTimestamp() {
		return System.currentTimeMillis() / 1000;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isIntegerStr(String string) {

		boolean result = false;

		if (isNotEmpty(string)) {
			try {
				if (0 < Integer.parseInt(string)) {
					result = true;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {

		boolean result = false;

		if (isNotEmpty(mobile) && isIntegerStr(mobile)) {
			Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
			Matcher matcher = pattern.matcher(mobile);
			result = matcher.matches();
		}

		return result;
	}

	/**
	 * 
	 * @param map
	 * @return true不为空|false=为空
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Map map) {

		boolean result = false;

		if (null != map && 0 < map.size()) {
			result = true;
		}

		return result;
	}

	/**
	 * 判断字符串是否为空(包括null和empty)
	 * 
	 * @param string 任意的字符串
	 * @return true不为空|false=为空
	 */
	public static boolean isNotEmpty(String string) {

		boolean result = false;

		if (null != string && 0 < string.length()) {
			result = true;
		}

		return result;
	}

	/**
	 * 创建目录且支持多级
	 * 如/sdcard/node1/node2/则会连续创建node1/和node2/
	 * 
	 * @param path 带路径的目录
	 * @return true=创建成功|false=失败
	 */
	public static boolean mkdirs(String path) {

		boolean result = false;

		if (isNotEmpty(path)) {
			File file = new File(path);
			if (null != file && !file.exists()) {
				result = file.mkdirs();
			}
		}

		return result;
	}
}
