package com.bqreaders.lib.token.serializer;

import com.bqreaders.lib.token.TokenInfo;
import com.bqreaders.lib.token.signer.TokenSigner;
import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Francisco Sanchez
 */
public class Base64TokenSerializerTest {
	private static final String SERIALIZED_TOKEN = "Serialized.token";
	private static final String BASE64_SERIALIZED_TOKEN = Base64.encodeBase64URLSafeString(SERIALIZED_TOKEN.getBytes());
	private static final String SIGN = "Signature";
	private long EXPIRE_TIME = 10L;
	private String HEX_EXPIRE_TIME = Long.toHexString(EXPIRE_TIME);

	private TokenInfo tokenInfo;
	private TokenSigner tokenSigner;

	@Before
	public void setup() {
		tokenInfo = mock(TokenInfo.class);
		when(tokenInfo.serialize()).thenReturn(SERIALIZED_TOKEN);

		tokenSigner = mock(TokenSigner.class);
		when(tokenSigner.sign(BASE64_SERIALIZED_TOKEN + "." + HEX_EXPIRE_TIME)).thenReturn(SIGN);
	}

	@Test
	public void testBase64TokenSerializer() {
		TokenSerializer testBase64TokenSerializer = new Base64TokenSerializer();
		String serializedToken = testBase64TokenSerializer.serialize(tokenInfo, EXPIRE_TIME, tokenSigner);
		assertThat(serializedToken).isEqualTo(BASE64_SERIALIZED_TOKEN + "." + HEX_EXPIRE_TIME + "." + SIGN);
	}
}
