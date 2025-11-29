package com.CSO2.contentservice.repository;

import com.CSO2.contentservice.model.FAQ;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FAQRepository extends MongoRepository<FAQ, String> {
}
