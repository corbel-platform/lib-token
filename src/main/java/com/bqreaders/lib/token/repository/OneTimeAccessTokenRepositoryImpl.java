package com.bqreaders.lib.token.repository;

import com.bqreaders.lib.token.model.OneTimeAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * Created by Alberto J. Rubio
 */
public class OneTimeAccessTokenRepositoryImpl implements CustomOneTimeAccessTokenRepository {

    private final MongoOperations mongoOperations;

    @Autowired
    public OneTimeAccessTokenRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public OneTimeAccessToken findAndRemove(String id) {
        Query query = new Query().addCriteria(Criteria.where("id").is(id));
        return mongoOperations.findAndRemove(query, OneTimeAccessToken.class);
    }
}
