/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.ioc;

import com.bqreaders.lib.token.parser.Base64BasicTokenParser;
import com.bqreaders.lib.token.parser.TokenParser;
import com.bqreaders.lib.token.serializer.Base64TokenSerializer;
import com.bqreaders.lib.token.serializer.TokenSerializer;
import com.bqreaders.lib.token.signer.HmacSha1TokenSigner;
import com.bqreaders.lib.token.signer.TokenSigner;
import com.bqreaders.lib.token.verifier.ExpirationTokenVerifier;
import com.bqreaders.lib.token.verifier.SignatureTokenVerifier;
import com.bqreaders.lib.token.verifier.TokenVerifier;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Alexander De Leon
 *
 */
@Configuration
public class TokenBasicIoc {

	/* -------------- Token basic Verifiers ------------- */
	@Bean
	public ExpirationTokenVerifier expireTokenVerifier() {
		return new ExpirationTokenVerifier();
	}

	@Bean
	public SignatureTokenVerifier signatureTokenVerifier(TokenSigner signer, TokenSerializer serializer) {
		return new SignatureTokenVerifier(signer, serializer);
	}

	/* --------------                       ------------- */

	@Bean
	public TokenSerializer tokenSerializer() {
		return new Base64TokenSerializer();
	}

	@Bean
	public TokenSigner tokenSigner(@Value("${token.signatureKey}") String signatureKey) {
		try {
			return new HmacSha1TokenSigner(signatureKey);
		} catch (Exception e) {
			throw new BeanCreationException("unable to create token signer", e);
		}
	}

	@Bean
	public TokenParser tokenParser(Collection<TokenVerifier> verifiers) {
		return new Base64BasicTokenParser(new ArrayList<>(verifiers));
	}

}
