/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.serializer;

import com.bqreaders.lib.token.TokenInfo;
import com.bqreaders.lib.token.signer.TokenSigner;

/**
 * @author Alexander De Leon
 * 
 */
public interface TokenSerializer {

	String serialize(TokenInfo info, long expireTime, TokenSigner signer);

}
