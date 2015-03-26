package com.bq.oss.lib.token.repository;

import com.bq.oss.lib.token.model.OneTimeAccessToken;

/**
 * Created Alberto J. Rubio
 */
public interface CustomOneTimeAccessTokenRepository {

	OneTimeAccessToken findAndRemove(String id);

	void deleteByTags(String... tags);
}
