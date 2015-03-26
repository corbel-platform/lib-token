package com.bq.oss.lib.token.repository;

import org.springframework.data.repository.CrudRepository;

import com.bq.oss.lib.token.model.OneTimeAccessToken;

/**
 * @author Alberto J. Rubio
 * 
 */
public interface OneTimeAccessTokenRepository extends CrudRepository<OneTimeAccessToken, String>,
		CustomOneTimeAccessTokenRepository {

}
