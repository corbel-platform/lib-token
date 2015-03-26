/*
 * Copyright (C) 2014 StarTIC
 */
package com.bq.oss.lib.token.parser;

import com.bq.oss.lib.token.exception.TokenVerificationException;
import com.bq.oss.lib.token.reader.TokenReader;

/**
 * @author Alexander De Leon
 * 
 */
public interface TokenParser {

	TokenReader parseAndVerify(String token) throws TokenVerificationException;

}
