/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.factory;

import com.bqreaders.lib.token.TokenGrant;
import com.bqreaders.lib.token.TokenInfo;

/**
 * @author Alexander De Leon
 * 
 */
public interface TokenFactory {

	/**
	 * Construct a new oauth token with the specified parameters.
	 * 
	 * @param info
	 *            Information encoded on the token
	 * @return
	 */
	TokenGrant createToken(TokenInfo info, long expiresIn);
}
