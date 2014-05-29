package com.bqreaders.lib.token.signer;

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Francisco Sanchez
 */
public class HmacSha1TokenSignerTest {
	private static final String SIGNER_KEY = "c2RmYXNkZmFzZGZhc2Rm";
	private static final String TEXT_TO_SIGN = "TEXT TO SIGN";
	private Mac mac;

	@Before
	public void setup() throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKeySpec signingKey = new SecretKeySpec(SIGNER_KEY.getBytes(), "HmacSHA1");
		mac = Mac.getInstance("HmacSHA1");
		mac.init(signingKey);
	}

	@Test
	public void testTokenExpired() throws InvalidKeyException, NoSuchAlgorithmException {
		TokenSigner tokenSigner = new HmacSha1TokenSigner(SIGNER_KEY);
		String signature = tokenSigner.sign(TEXT_TO_SIGN);
		String localSignature = Base64.encodeBase64URLSafeString(mac.doFinal(TEXT_TO_SIGN.getBytes()));
		assertThat(signature).isEqualTo(localSignature);
	}

}
