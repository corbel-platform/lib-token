/*
 * Copyright (C) 2014 StarTIC
 */
package com.bq.oss.lib.token.signer;

/**
 * @author Alexander De Leon
 * 
 */
public interface TokenSigner {

	String sign(String token);
}
