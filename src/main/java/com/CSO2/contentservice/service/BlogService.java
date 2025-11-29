package com.CSO2.contentservice.service;

import com.CSO2.contentservice.model.BlogPost;
import com.CSO2.contentservice.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getAllPublishedPosts() {
        return blogPostRepository.findByIsPublishedTrue();
    }

    public List<BlogPost> getFeaturedPosts() {
        return blogPostRepository.findByFeaturedTrueAndIsPublishedTrue();
    }

    public Page<BlogPost> getPosts(Pageable pageable) {
        return blogPostRepository.findAll(pageable);
    }

    public Optional<BlogPost> getPostBySlug(String slug) {
        return blogPostRepository.findBySlug(slug);
    }

    public BlogPost createPost(BlogPost post) {
        return blogPostRepository.save(post);
    }
}
