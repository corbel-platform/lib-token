package io.corbel.lib.token.verifier;

import io.corbel.lib.token.exception.TokenVerificationException;
import io.corbel.lib.token.reader.TokenReader;
import io.corbel.lib.token.serializer.TokenSerializer;
import io.corbel.lib.token.signer.TokenSigner;

/**
 * @author Alexander De Leon
 * 
 */
public class SignatureTokenVerifier implements TokenVerifier {

	private final TokenSigner signer;
	private final TokenSerializer serializer;

	public SignatureTokenVerifier(TokenSigner signer, TokenSerializer serializer) {
		this.signer = signer;
		this.serializer = serializer;
	}

	@Override
	public void verify(TokenReader reader) throws TokenVerificationException {
		if (!serializer.serialize(reader.getInfo(), reader.getExpireTime(), signer).equals(reader.getToken())) {
			throw new TokenVerificationException.InvalidSignature();
		}
	}
}
