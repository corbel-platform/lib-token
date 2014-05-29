/*
 * Copyright (C) 2014 StarTIC
 */
package com.bqreaders.lib.token.model;

/**
 * @author Alexander De Leon
 * 
 */
public enum TokenType {

	CODE,
	TOKEN,
    REFRESH,
    COOKIE,
	INVALID;

	public static TokenType fromString(String name) {
		try {
			return name == null ? null : Enum.valueOf(TokenType.class, name.toUpperCase());
		} catch (IllegalArgumentException e) {
			return INVALID;
		}
	}
}
