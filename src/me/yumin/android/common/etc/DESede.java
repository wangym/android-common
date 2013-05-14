/**
 * 
 */
package me.yumin.android.common.etc;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import me.yumin.android.common.thirdparty.Base64;

/**
 * @author yumin
 * 
 */
public class DESede {

	/**
	 * 
	 */
	private static final String Algorithm = "DESede/CBC/PKCS5Padding";

	/**
	 * 
	 * @param string
	 * @param secret
	 * @return
	 */
	public static byte[] encrypt(String string, String secret) {

		byte[] bytes = null;

		try {
			SecretKey deskey = new SecretKeySpec(secret.getBytes(), "DESede");
			Cipher c1 = Cipher.getInstance(Algorithm);
			IvParameterSpec iv = new IvParameterSpec(secret.substring(0, 8).getBytes());
			c1.init(Cipher.ENCRYPT_MODE, deskey, iv);
			bytes = new Base64().encode(c1.doFinal(string.getBytes()));
		} catch (NoSuchAlgorithmException noSuchAlgorithmException) {
			noSuchAlgorithmException.printStackTrace();
		} catch (NoSuchPaddingException noSuchPaddingException) {
			noSuchPaddingException.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bytes;
	}
}
