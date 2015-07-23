package io.corbel.lib.token.repository;

import io.corbel.lib.token.model.OneTimeAccessToken;

/**
 * @author Alberto J. Rubio
 */
public interface CustomOneTimeAccessTokenRepository {

	OneTimeAccessToken findAndRemove(String id);

	void deleteByTags(String... tags);
}
