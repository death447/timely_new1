package com.timely.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.timely.common.utils.base64.Base64;

public abstract class AES {
	// 密钥
	private static final byte[] AES_KEY = "F8L7UX797S4V40JI".getBytes();
	// 偏移向量串
	private static final byte[] AES_IV = "6694PIPVG15GQ24G".getBytes();
	// 暗号
	private static final String CIPHER = "AES/CBC/PKCS5Padding";
	// 编码方式
	private static final String CHARSET = "ASCII";
	// 加解密方式
	private static final String AES_TYPE = "AES";
	/** @category 日志记录器 */
	// private static SJDFLogger logger =
	// SJDFLoggerFactory.getSJDFLogger(AES.class);

	/**
	 * @param s
	 *            source str
	 * @return encrypt str
	 * @category AES加密
	 */
	public static String encrypt(String s) {
        // AES加密
        SecretKeySpec skeySpec = new SecretKeySpec(AES.AES_KEY, AES_TYPE);
        IvParameterSpec iv = new IvParameterSpec(AES.AES_IV);
        byte[] result;
        try {
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            result = cipher.doFinal(s.getBytes());
            // BASE64
            return new String(Base64.encode(result), CHARSET);
        } catch (Exception e) {
            //logger.error("AES加密失败", e);
            throw new RuntimeException("AES加密失败", e);
        }
    }

	/**
	 * @param s
	 *            source
	 * @return decrypt str
	 * @category AES解密
	 */
	public static String decrypt(String s) {
        IvParameterSpec iv = new IvParameterSpec(AES.AES_IV);
        SecretKeySpec skeySpec = new SecretKeySpec(AES.AES_KEY, AES_TYPE);
        try {
            // BASE64
            byte[] resource = Base64.decode(s.getBytes(CHARSET));
            Cipher cipher = Cipher.getInstance(CIPHER);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            return new String(cipher.doFinal(resource));
        } catch (Exception e) {
            //logger.error("AES解密失败", e);
            throw new RuntimeException("AES解密失败", e);
        }
    }
}
