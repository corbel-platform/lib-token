package com.bqreaders.lib.token.factory;

import com.bqreaders.lib.token.TokenGrant;
import com.bqreaders.lib.token.TokenInfo;
import com.bqreaders.lib.token.model.OneTimeAccessToken;
import com.bqreaders.lib.token.repository.OneTimeAccessTokenRepository;
import com.bqreaders.lib.token.serializer.TokenSerializer;
import com.bqreaders.lib.token.signer.TokenSigner;
import org.joda.time.Instant;

import java.util.concurrent.TimeUnit;

/**
 * @author Francisco Sanchez
 */
public class BasicTokenFactory implements TokenFactory {

	private final TokenSigner signer;
	private final TokenSerializer serializer;
	private final OneTimeAccessTokenRepository oneTimeAccessTokenRepository;

	public BasicTokenFactory(TokenSigner signer, TokenSerializer serializer,
			OneTimeAccessTokenRepository oneTimeAccessTokenRepository) {
		this.signer = signer;
		this.serializer = serializer;
		this.oneTimeAccessTokenRepository = oneTimeAccessTokenRepository;
	}

	@Override
	public TokenGrant createToken(TokenInfo info, long expiresIn) {
		Instant expireTime = Instant.now().plus(TimeUnit.SECONDS.toMillis(expiresIn));
		long expiresAt = expireTime.getMillis();
		String token = serializer.serialize(info, expiresAt, signer);
		if (info.isOneUseToken()) {
			oneTimeAccessTokenRepository.save(new OneTimeAccessToken(token, expireTime.toDate()));
		}
		return new TokenGrant(token, expiresIn);
	}
}
