package com.bqreaders.lib.token.ioc;

import com.bqreaders.lib.token.factory.BasicTokenFactory;
import com.bqreaders.lib.token.factory.TokenFactory;
import com.bqreaders.lib.token.repository.OneTimeAccessTokenRepository;
import com.bqreaders.lib.token.serializer.TokenSerializer;
import com.bqreaders.lib.token.signer.TokenSigner;
import com.bqreaders.lib.token.verifier.OneTimeAccessTokenVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Alberto J. Rubio
 */
@Configuration
@Import({TokenBasicIoc.class})
public class TokenIoc {

    @Bean
    public OneTimeAccessTokenVerifier oneTimeAccessTokenVerifier(
            OneTimeAccessTokenRepository oneTimeAccessTokenRepository) {
        return new OneTimeAccessTokenVerifier(oneTimeAccessTokenRepository);
    }

    @Bean
    public TokenFactory tokenFactory(TokenSigner tokenSigner, TokenSerializer tokenSerializer,
                                     OneTimeAccessTokenRepository oneTimeAccessTokenRepository) {
        return new BasicTokenFactory(tokenSigner, tokenSerializer, oneTimeAccessTokenRepository);
    }

}
