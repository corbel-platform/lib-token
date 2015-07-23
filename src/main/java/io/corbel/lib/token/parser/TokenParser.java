package io.corbel.lib.token.parser;

import io.corbel.lib.token.exception.TokenVerificationException;
import io.corbel.lib.token.reader.TokenReader;

/**
 * @author Alexander De Leon
 * 
 */
public interface TokenParser {

	TokenReader parseAndVerify(String token) throws TokenVerificationException;

}
