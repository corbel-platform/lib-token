/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.verifier;

import com.bqreaders.lib.token.exception.TokenVerificationException;
import com.bqreaders.lib.token.reader.TokenReader;

/**
 * @author Alexander De Leon
 * 
 */
public interface TokenVerifier {

	void verify(TokenReader reader) throws TokenVerificationException;

}
