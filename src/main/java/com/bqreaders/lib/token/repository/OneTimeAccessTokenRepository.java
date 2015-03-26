package com.bqreaders.lib.token.repository;

import org.springframework.data.repository.CrudRepository;

import com.bqreaders.lib.token.model.OneTimeAccessToken;

/**
 * @author Alberto J. Rubio
 * 
 */
public interface OneTimeAccessTokenRepository extends CrudRepository<OneTimeAccessToken, String>,
		CustomOneTimeAccessTokenRepository {

}
