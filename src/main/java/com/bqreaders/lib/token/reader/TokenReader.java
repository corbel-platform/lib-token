/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.reader;

import com.bqreaders.lib.token.TokenInfo;

/**
 * @author Alexander De Leon
 * 
 */
public interface TokenReader {

	TokenInfo getInfo();

	long getExpireTime();

	String getSignature();

	String getToken();

}
