/*
 * Copyright (C) 2014 StarTIC
 */
package com.bq.oss.lib.token.ioc;

import java.time.Clock;

import com.bq.oss.lib.mongo.config.MongoCommonRepositoryFactoryBean;
import com.bq.oss.lib.token.factory.BasicTokenFactory;
import com.bq.oss.lib.token.factory.TokenFactory;
import com.bq.oss.lib.token.repository.OneTimeAccessTokenRepository;
import com.bq.oss.lib.token.serializer.TokenSerializer;
import com.bq.oss.lib.token.signer.TokenSigner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.bq.oss.lib.token.verifier.OneTimeAccessTokenVerifier;

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
