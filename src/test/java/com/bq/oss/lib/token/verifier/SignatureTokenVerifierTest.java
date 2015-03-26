/*
 * Copyright (C) 2014 StarTIC
 */
package com.bq.oss.lib.token.verifier;

import com.bq.oss.lib.token.signer.TokenSigner;
import com.bq.oss.lib.token.TokenInfo;
import com.bq.oss.lib.token.exception.TokenVerificationException;
import com.bq.oss.lib.token.model.TokenType;
import com.bq.oss.lib.token.reader.TokenReader;
import com.bq.oss.lib.token.serializer.TokenSerializer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander De Leon
 * 
 */
public class SignatureTokenVerifierTest {

	private static final long TEST_EXPIRE = 10;
	private static final String TEST_TOKEN = "xxx";
	private SignatureTokenVerifier verifier;
	private TokenSigner signerMock;
	private TokenSerializer serializerMock;

	@Before
	public void setup() {
		signerMock = mock(TokenSigner.class);
		serializerMock = mock(TokenSerializer.class);
		when(serializerMock.serialize(Mockito.any(TokenInfo.class), Mockito.eq(TEST_EXPIRE), Mockito.same(signerMock)))
				.thenReturn(TEST_TOKEN);
		verifier = new SignatureTokenVerifier(signerMock, serializerMock);
	}

	@Test(expected = TokenVerificationException.InvalidSignature.class)
	public void testInvalidSignature() throws TokenVerificationException {
		TokenInfo info = TokenInfo.newBuilder().setType(TokenType.CODE).setUserId("a").setClientId("b").build();
		TokenReader reader = mock(TokenReader.class);
		when(reader.getToken()).thenReturn("yyyy");
		when(reader.getInfo()).thenReturn(info);
		when(reader.getExpireTime()).thenReturn(TEST_EXPIRE);
		verifier.verify(reader);
	}

	@Test()
	public void testValidSignature() throws TokenVerificationException {
		TokenInfo info = TokenInfo.newBuilder().setType(TokenType.CODE).setUserId("a").setClientId("b").build();
		TokenReader reader = mock(TokenReader.class);
		when(reader.getToken()).thenReturn(TEST_TOKEN);
		when(reader.getInfo()).thenReturn(info);
		when(reader.getExpireTime()).thenReturn(TEST_EXPIRE);
		verifier.verify(reader);
	}
}
