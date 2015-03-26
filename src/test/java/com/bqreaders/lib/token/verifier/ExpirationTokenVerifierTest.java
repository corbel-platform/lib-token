/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.verifier;

import com.bqreaders.lib.token.exception.TokenVerificationException;
import com.bqreaders.lib.token.reader.TokenReader;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Alexander De Leon
 * 
 */
public class ExpirationTokenVerifierTest {

	private ExpirationTokenVerifier verifier;

	@Before
	public void setup() {
		verifier = new ExpirationTokenVerifier();
	}

	@Test(expected = TokenVerificationException.TokenExpired.class)
	public void testTokenExpired() throws TokenVerificationException {
		TokenReader reader = mock(TokenReader.class);
		when(reader.getExpireTime()).thenReturn(System.currentTimeMillis() - 1);
		verifier.verify(reader);
	}

	@Test
	public void testOk() throws TokenVerificationException {
		TokenReader reader = mock(TokenReader.class);
		when(reader.getExpireTime()).thenReturn(System.currentTimeMillis() + 1000);
		verifier.verify(reader);
	}

}
