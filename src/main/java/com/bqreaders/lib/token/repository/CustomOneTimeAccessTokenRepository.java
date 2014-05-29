package com.bqreaders.lib.token.repository;

import com.bqreaders.lib.token.model.OneTimeAccessToken;

/**
 * Created Alberto J. Rubio
 */
public interface CustomOneTimeAccessTokenRepository {

    OneTimeAccessToken findAndRemove(String id);
}
