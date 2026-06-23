package com.ms.schneider.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ms.schneider.entity.CustomerMongoData;

@Repository
public interface CustomerMongoRepository extends MongoRepository<CustomerMongoData, String> {

}
