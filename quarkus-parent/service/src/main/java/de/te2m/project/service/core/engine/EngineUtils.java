/*
 * EngineUtils.java
 *
 * Copyright 2009 - 2014 Frank Fischer (email: frank@te2m.de)
 *
 * This file is part of the te2m-t4p-web project which is a sub project of temtools
 * (http://temtools.sf.net).
 *
 */
package de.te2m.project.service.core.engine;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * DOCUMENT ME!.
 *
 * @author $author$
 * @version $Revision$
 */
public class EngineUtils {

	/**
	 * generates a md5 hash from a string.
	 *
	 * @param value the value
	 * @param salt  the salt
	 * @return - a md5 hash as a a string
	 */
	public static String createMD5Hash(String value, String salt) {
		StringBuffer hexString = new StringBuffer();

		if (null == salt) {
			salt = "";
		}

		try {
			String tmp = value + salt;

			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.reset();
			md5.update(tmp.getBytes());

			byte[] result = md5.digest();

			for (int i = 0; i < result.length; i++) {
				String hs = Integer.toHexString(0xFF & result[i]);

				if (hs.length() == 0) {
					hexString.append("00");
				} else if (hs.length() == 1) {
					hexString.append("0");
				}

				hexString.append(hs);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hexString.toString();
	}

	/**
	 * generates a md5 hash from a string.
	 *
	 * @param value the value
	 * @return - a md5 hash as a a string
	 */
	public static String createMD5Hash(String value) {
		return createMD5Hash(value, null);
	}
}
