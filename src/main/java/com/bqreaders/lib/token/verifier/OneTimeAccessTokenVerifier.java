/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.verifier;

import com.bqreaders.lib.token.exception.TokenVerificationException;
import com.bqreaders.lib.token.reader.TokenReader;
import com.bqreaders.lib.token.repository.OneTimeAccessTokenRepository;

/**
 * @author Alberto J. Rubio
 * 
 */
public class OneTimeAccessTokenVerifier implements TokenVerifier {

	private final OneTimeAccessTokenRepository oneTimeAccessTokenRepository;

	public OneTimeAccessTokenVerifier(OneTimeAccessTokenRepository oneTimeAccessTokenRepository) {
		this.oneTimeAccessTokenRepository = oneTimeAccessTokenRepository;
	}

	@Override
	public void verify(TokenReader reader) throws TokenVerificationException {
		if (reader.getInfo().isOneUseToken()) {
			if (oneTimeAccessTokenRepository.findAndRemove(reader.getToken()) == null) {
				throw new TokenVerificationException.TokenNotFound();
			}
		}
	}
}
