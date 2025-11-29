package com.CSO2.contentservice.repository;

import com.CSO2.contentservice.model.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogPostRepository extends MongoRepository<BlogPost, String> {
    Optional<BlogPost> findBySlug(String slug);

    List<BlogPost> findByIsPublishedTrue();

    List<BlogPost> findByFeaturedTrueAndIsPublishedTrue();
}
