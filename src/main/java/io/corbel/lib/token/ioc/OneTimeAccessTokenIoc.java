package io.corbel.lib.token.ioc;

import java.time.Clock;

import io.corbel.lib.mongo.config.MongoCommonRepositoryFactoryBean;
import io.corbel.lib.token.factory.BasicTokenFactory;
import io.corbel.lib.token.factory.TokenFactory;
import io.corbel.lib.token.repository.OneTimeAccessTokenRepository;
import io.corbel.lib.token.serializer.TokenSerializer;
import io.corbel.lib.token.signer.TokenSigner;
import io.corbel.lib.token.verifier.OneTimeAccessTokenVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Alexander De Leon
 *
 */
@Configuration
@EnableMongoRepositories(value = { "com.bq.oss.lib.token.repository" }, repositoryFactoryBeanClass = MongoCommonRepositoryFactoryBean.class)
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
