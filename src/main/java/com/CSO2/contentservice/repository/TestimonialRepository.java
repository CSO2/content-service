package com.CSO2.contentservice.repository;

import com.CSO2.contentservice.model.Testimonial;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends MongoRepository<Testimonial, String> {
}
