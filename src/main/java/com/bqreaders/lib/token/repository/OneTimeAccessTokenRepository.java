package com.bqreaders.lib.token.repository;

import com.bqreaders.lib.token.model.OneTimeAccessToken;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Alberto J. Rubio
 * 
 */
public interface OneTimeAccessTokenRepository extends CrudRepository<OneTimeAccessToken, String>,
		CustomOneTimeAccessTokenRepository {

}
