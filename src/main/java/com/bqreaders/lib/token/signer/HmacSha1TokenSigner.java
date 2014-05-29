/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.signer;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Alexander De Leon
 * 
 */
public class HmacSha1TokenSigner implements TokenSigner {

	private static final String HMAC_SHA1 = "HmacSHA1";
	private final Mac mac;

	public HmacSha1TokenSigner(String signatureKey) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec signingKey = new SecretKeySpec(signatureKey.getBytes(), HMAC_SHA1);
		this.mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
	}

	@Override
	public String sign(String token) {
		byte[] rawHmac = mac.doFinal(token.getBytes());
		return Base64.encodeBase64URLSafeString(rawHmac);
	}

}
