/*
 * Copyright (C) 2014 StarTIC
 */
package com.bq.oss.lib.token.serializer;

import com.bq.oss.lib.token.signer.TokenSigner;
import com.bq.oss.lib.token.TokenInfo;

/**
 * @author Alexander De Leon
 * 
 */
public interface TokenSerializer {

	String serialize(TokenInfo info, long expireTime, TokenSigner signer);

}
