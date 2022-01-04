package com.ber.template.service.impl;

import com.ber.template.domain.MongoDome;
import com.ber.template.service.MongoDomeService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoDomeServiceImpl implements MongoDomeService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<MongoDome> getAll() {
        return mongoTemplate.findAll(MongoDome.class);
    }

    @Override
    public Boolean addOrUpdate(MongoDome mongoDome) {
        MongoDome newMongoDome = null;
        MongoDome mongo = getByName(mongoDome.getName());
        if (mongo != null) {
            Query query = new Query();
            query.addCriteria(Criteria.where("name").is(mongoDome.getName()));
            Update update = new Update();
            update.set("name", mongoDome.getName());
            update.set("remark", mongoDome.getRemark());
            UpdateResult updateResult = mongoTemplate.updateFirst(query, update, MongoDome.class);
            System.out.println(updateResult);
        } else {
            newMongoDome = mongoTemplate.insert(mongoDome);
        }
        return newMongoDome != null ? true : false;
    }

    @Override
    public Boolean delete(MongoDome mongoDome) {
        Query query = new Query();
        if (mongoDome.getId() != null) {
            query.addCriteria(Criteria.where("_id").is(mongoDome.getId()));
            DeleteResult deleteResult = mongoTemplate.remove(query, MongoDome.class);
            System.out.println(deleteResult);
        } else {
            query.addCriteria(Criteria.where("name").is(mongoDome.getName()));
            query.addCriteria(Criteria.where("remark").is(mongoDome.getRemark()));
            mongoTemplate.findAllAndRemove(query, MongoDome.class);
        }
        return null;
    }

    @Override
    public MongoDome getByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        MongoDome mongoDome = mongoTemplate.findOne(query, MongoDome.class);
        return mongoDome;
    }
}
