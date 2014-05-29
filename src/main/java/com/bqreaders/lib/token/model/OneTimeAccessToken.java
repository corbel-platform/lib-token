package com.bqreaders.lib.token.model;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Objects;

/**
 * @author Alberto J. Rubio
 * 
 */
public class OneTimeAccessToken {

	@Id
	private final String id;
	private final Date expireAt;

	public OneTimeAccessToken(String id, Date expireAt) {
		this.id = id;
		this.expireAt = expireAt;
	}

	public String getId() {
		return id;
	}

	public Date getExpireAt() {
		return expireAt;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, expireAt);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof OneTimeAccessToken)) {
			return false;
		}
		OneTimeAccessToken that = (OneTimeAccessToken) obj;
		return Objects.equals(this.id, that.id) && Objects.equals(this.expireAt, that.expireAt);
	}

}
