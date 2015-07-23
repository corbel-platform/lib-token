package io.corbel.lib.token.serializer;

import io.corbel.lib.token.TokenInfo;
import io.corbel.lib.token.signer.TokenSigner;

/**
 * @author Alexander De Leon
 * 
 */
public interface TokenSerializer {

	String serialize(TokenInfo info, long expireTime, TokenSigner signer);

}
