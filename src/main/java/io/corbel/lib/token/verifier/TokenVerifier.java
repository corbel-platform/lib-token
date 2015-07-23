package io.corbel.lib.token.verifier;

import io.corbel.lib.token.exception.TokenVerificationException;
import io.corbel.lib.token.reader.TokenReader;

/**
 * @author Alexander De Leon
 * 
 */
public interface TokenVerifier {

	void verify(TokenReader reader) throws TokenVerificationException;

}
