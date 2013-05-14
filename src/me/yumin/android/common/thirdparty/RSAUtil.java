package me.yumin.android.common.thirdparty;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;


  
  
  
/**  
 * RSA 工具类。提供加密，解密，生成密钥对等方法。  
 *  
 */  
public class RSAUtil { 
	   public static final int KEY_SIZE = 512;
	   public static RSAPublicKey pubKey ;
	   /**
	    * 生成公钥
	    */
	   public static RSAPublicKey generateRSAPublicKey(BigInteger modulus,   
		    		BigInteger publicExponent) throws Exception {   
		     RSAPublicKey pubKey;   
		        try {   
		    		KeyFactory fact = KeyFactory.getInstance("RSA","BC");
		    		RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(modulus,publicExponent );
		    		pubKey = (RSAPublicKey)fact.generatePublic(pubKeySpec);
		        } catch (NoSuchAlgorithmException ex) {   
		            throw new Exception(ex.getMessage());   
		        }   
		     return pubKey;
		 }   
	   /**
	    * 生成公钥
	    */
	   public static RSAPublicKey generateRSAPublicKey(String spubKey) {
			if (spubKey!=null && !spubKey.equals("")) {
				BufferedReader br = new BufferedReader(new StringReader(spubKey));
				BigInteger mypubkey_modulus = null;
				BigInteger mypubkey_exponent = null;
				String readstr = null;

				try {
					readstr = br.readLine();
					mypubkey_modulus = new BigInteger(readstr);

					readstr = br.readLine();
					mypubkey_exponent = new BigInteger(readstr);

					pubKey = generateRSAPublicKey(mypubkey_modulus, mypubkey_exponent);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return pubKey;
		}
	   
	   
		public static String encrypt(String inputStr) throws Exception {

			byte[] out = encrypt(pubKey, inputStr.getBytes());
			String resultStr = HexUtil.bytesToHexString(out);
			return resultStr;
		}

		/**
		 * * 加密 *
		 * 
		 * @param key
		 *            加密的密钥 *
		 * @param data
		 *            待加密的明文数据 *
		 * @return 加密后的数据 *
		 * @throws Exception
		 */
		 public static byte[] encrypt(PublicKey pk, byte[] data) throws Exception {
			try {
		        SecureRandom  rand = new FixedSecureRandom();
		        Cipher cipher = Cipher.getInstance("RSA","BC");	        
				cipher.init(Cipher.ENCRYPT_MODE,pk,rand);          
				int blockSize = cipher.getBlockSize();
				int outputSize = cipher.getOutputSize(data.length);// 获得加密块加密后块大小
				int leavedSize = data.length % blockSize;
				int blocksSize = leavedSize != 0 ? data.length / blockSize + 1
						: data.length / blockSize;
				byte[] raw = new byte[outputSize * blocksSize];
				int i = 0;
				while (data.length - i * blockSize > 0) {
					if (data.length - i * blockSize > blockSize)
						cipher.doFinal(data, i * blockSize, blockSize, raw, i
								* outputSize);
					else
						cipher.doFinal(data, i * blockSize, data.length - i
								* blockSize, raw, i * outputSize);
					i++;
				}
				return raw;
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}

		/**
		 * * 解密 *
		 * 
		 * @param key
		 *            解密的密钥 *
		 * @param raw
		 *            已经加密的数据 *
		 * @return 解密后的明文 *
		 * @throws Exception
		 */
		public static byte[] decrypt(PrivateKey pk, byte[] raw) throws Exception {
			try {
				Cipher cipher = Cipher.getInstance("RSA","BC");

				cipher.init(Cipher.DECRYPT_MODE, pk);
				int blockSize = cipher.getBlockSize();
				ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
				int j = 0;

				while (raw.length - j * blockSize > 0) {
					bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
					j++;
				}
				return bout.toByteArray();
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
	}