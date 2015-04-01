/*
 * Copyright (C) 2014 StarTIC
 */
package com.bq.oss.lib.token.provider;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.ws.rs.CookieParam;
import javax.ws.rs.core.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bq.oss.lib.token.exception.TokenVerificationException;
import com.bq.oss.lib.token.parser.TokenParser;
import com.bq.oss.lib.token.reader.TokenReader;
import com.google.common.base.Optional;
import com.sun.jersey.api.core.HttpContext;
import com.sun.jersey.api.model.Parameter;
import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.server.impl.inject.AbstractHttpContextInjectable;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

/**
 * @author Alexander De Leon
 *
 */
public class SessionProvider implements InjectableProvider<CookieParam, Parameter> {

	private static final Logger LOG = LoggerFactory.getLogger(SessionProvider.class);

	private final TokenParser tokenParser;

	public SessionProvider(TokenParser tokenParser) {
		this.tokenParser = tokenParser;
	}

	@Override
	public ComponentScope getScope() {
		return ComponentScope.PerRequest;
	}

	@Override
	public Injectable<?> getInjectable(ComponentContext componentContext, CookieParam cookieAnnotation,
			Parameter parameter) {
		String cookieKey = cookieAnnotation.value();
		if (isOptionalSession(parameter.getParameterType())) {
			return new OptionalSessionInjectable(cookieKey);
		}
		if (TokenReader.class.isAssignableFrom(parameter.getParameterClass())) {
			return new SessionInjectable(cookieKey);
		}
		return null;
	}

	private boolean isOptionalSession(Type parameterType) {
		if (parameterType instanceof ParameterizedType) {
			ParameterizedType generic = (ParameterizedType) parameterType;
			if (generic.getRawType().equals(Optional.class)) {
				return generic.getActualTypeArguments()[0].equals(TokenReader.class);
			}
		}
		return false;
	}

	private class OptionalSessionInjectable extends AbstractHttpContextInjectable<Optional<TokenReader>> {

		private final SessionInjectable sessionInjectable;

		public OptionalSessionInjectable(String cookieKey) {
			sessionInjectable = new SessionInjectable(cookieKey);
		}

		@Override
		public Optional<TokenReader> getValue(HttpContext context) {
			return Optional.fromNullable(sessionInjectable.getValue(context));
		}

	}

	private class SessionInjectable extends AbstractHttpContextInjectable<TokenReader> {

		private final String cookieKey;

		public SessionInjectable(String cookieKey) {
			this.cookieKey = cookieKey;
		}

		@Override
		public TokenReader getValue(HttpContext context) {
			Cookie cookie = context.getRequest().getCookies().get(cookieKey);
			if (cookie != null) {
				try {
					return tokenParser.parseAndVerify(cookie.getValue());
				} catch (TokenVerificationException e) {
					LOG.warn("Received invalid session cookie {}", cookie);
				}
			}
			return null;
		}
	}

}