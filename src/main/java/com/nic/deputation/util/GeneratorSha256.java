/**
 * @author ARJUN SINGH
 */
package com.nic.deputation.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GeneratorSha256 implements PasswordEncoder {
	private final Log logger = LogFactory.getLog(getClass());

	/**
	 * 
	 * @param valueTocovertSha
	 *            as input of type String to convert Sha256 code.
	 * @return This method will return String type value after apply Sha256
	 *         algorithm.
	 * @throws NoSuchAlgorithmException
	 *             It will throw two types(NoSuchAlgorithmException and
	 *             NullPointerException) Exceptions.
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		String shaKey = null;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rawPassword != null) {
			md.update(rawPassword.toString().getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format method
			StringBuffer stringBuffer = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				stringBuffer.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			shaKey = stringBuffer.toString();
		} else {
			new NullPointerException();
		}
		return shaKey;

	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword == null || encodedPassword.length() == 0) {
			logger.warn("Empty encoded password");
			return false;
		}

		/*
		 * if (!BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
		 * logger.warn("Encoded password does not look like BCrypt"); return false; }
		 */

		return rawPassword.toString().equals(encodedPassword);
	}
}
