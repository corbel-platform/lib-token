/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.parser;

import com.bqreaders.lib.token.TokenInfo;
import com.bqreaders.lib.token.exception.TokenVerificationException;
import com.bqreaders.lib.token.reader.TokenReader;
import com.bqreaders.lib.token.serializer.Base64TokenSerializer;
import com.bqreaders.lib.token.verifier.TokenVerifier;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.Validate;

import java.util.List;

/**
 * @author Alexander De Leon
 * 
 */
public class Base64BasicTokenParser implements TokenParser {

	private final List<TokenVerifier> verifiers;

	public Base64BasicTokenParser(List<TokenVerifier> verifiers) {
		this.verifiers = verifiers;
	}

	@Override
	public TokenReader parseAndVerify(String token) throws TokenVerificationException {
		try {
			TokenReader reader = new Reader(token);
			for (TokenVerifier verifier : verifiers) {
				verifier.verify(reader);
			}
			return reader;
		} catch (IllegalArgumentException e) {
			throw new TokenVerificationException("Invalid token", e);
		}
	}

	private static class Reader implements TokenReader {

		private final String token;
		private final TokenInfo info;
		private final long expire;
		private final String signature;

		public Reader(String token) {
			this.token = token;
			String[] parts = token.split(Base64TokenSerializer.SEPARATOR_REGEX);
			Validate.isTrue(parts.length == 3);
			info = TokenInfo.deserialize(decode(parts[0]));
			expire = Long.parseLong(parts[1], 16);
			signature = decode(parts[2]);
		}

		@Override
		public TokenInfo getInfo() {
			return info;
		}

		@Override
		public long getExpireTime() {
			return expire;
		}

		@Override
		public String getSignature() {
			return signature;
		}

		@Override
		public String getToken() {
			return token;
		}

		private String decode(String string) {
			return new String(Base64.decodeBase64(string.getBytes()));
		}

	}
}
