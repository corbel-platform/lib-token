/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.ioc;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.bqreaders.lib.token.factory.BasicTokenFactory;
import com.bqreaders.lib.token.factory.TokenFactory;
import com.bqreaders.lib.token.repository.OneTimeAccessTokenRepository;
import com.bqreaders.lib.token.serializer.TokenSerializer;
import com.bqreaders.lib.token.signer.TokenSigner;
import com.bqreaders.lib.token.verifier.OneTimeAccessTokenVerifier;
import com.bqreaders.silkroad.mongo.config.MongoCommonRepositoryFactoryBean;

/**
 * @author Alexander De Leon
 *
 */
@Configuration
@EnableMongoRepositories(value = { "com.bqreaders.lib.token.repository" }, repositoryFactoryBeanClass = MongoCommonRepositoryFactoryBean.class)
public class OneTimeAccessTokenIoc {

	@Bean
	public OneTimeAccessTokenVerifier oneTimeAccessTokenVerifier(
			OneTimeAccessTokenRepository oneTimeAccessTokenRepository) {
		return new OneTimeAccessTokenVerifier(oneTimeAccessTokenRepository);
	}

	@Bean
	public TokenFactory tokenFactory(TokenSigner tokenSigner, TokenSerializer tokenSerializer,
			OneTimeAccessTokenRepository oneTimeAccessTokenRepository) {
		return new BasicTokenFactory(tokenSigner, tokenSerializer, oneTimeAccessTokenRepository, Clock.systemUTC());
	}

}
