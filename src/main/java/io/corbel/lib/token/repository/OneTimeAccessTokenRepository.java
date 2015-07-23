package io.corbel.lib.token.repository;

import org.springframework.data.repository.CrudRepository;

import io.corbel.lib.token.model.OneTimeAccessToken;

/**
 * @author Alberto J. Rubio
 * 
 */
public interface OneTimeAccessTokenRepository extends CrudRepository<OneTimeAccessToken, String>,
		CustomOneTimeAccessTokenRepository {

}
