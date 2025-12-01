package com.CSO2.contentservice.service;

import com.CSO2.contentservice.model.BlogPost;
import com.CSO2.contentservice.repository.BlogPostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlogServiceTest {

    @Mock
    private BlogPostRepository blogPostRepository;

    @InjectMocks
    private BlogService blogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllPublishedPosts_ShouldReturnPublishedPosts() {
        BlogPost post1 = new BlogPost();
        post1.setPublished(true);
        BlogPost post2 = new BlogPost();
        post2.setPublished(true);
        when(blogPostRepository.findByIsPublishedTrue()).thenReturn(Arrays.asList(post1, post2));

        List<BlogPost> result = blogService.getAllPublishedPosts();

        assertEquals(2, result.size());
        verify(blogPostRepository, times(1)).findByIsPublishedTrue();
    }

    @Test
    void getFeaturedPosts_ShouldReturnFeaturedAndPublishedPosts() {
        BlogPost post1 = new BlogPost();
        post1.setFeatured(true);
        post1.setPublished(true);
        when(blogPostRepository.findByFeaturedTrueAndIsPublishedTrue()).thenReturn(Arrays.asList(post1));

        List<BlogPost> result = blogService.getFeaturedPosts();

        assertEquals(1, result.size());
        verify(blogPostRepository, times(1)).findByFeaturedTrueAndIsPublishedTrue();
    }

    @Test
    void getPosts_ShouldReturnPageOfPosts() {
        BlogPost post1 = new BlogPost();
        Pageable pageable = PageRequest.of(0, 10);
        Page<BlogPost> page = new PageImpl<>(Arrays.asList(post1));
        when(blogPostRepository.findAll(pageable)).thenReturn(page);

        Page<BlogPost> result = blogService.getPosts(pageable);

        assertEquals(1, result.getTotalElements());
        verify(blogPostRepository, times(1)).findAll(pageable);
    }

    @Test
    void getPostBySlug_ShouldReturnPost_WhenFound() {
        String slug = "test-slug";
        BlogPost post = new BlogPost();
        post.setSlug(slug);
        when(blogPostRepository.findBySlug(slug)).thenReturn(Optional.of(post));

        Optional<BlogPost> result = blogService.getPostBySlug(slug);

        assertTrue(result.isPresent());
        assertEquals(slug, result.get().getSlug());
        verify(blogPostRepository, times(1)).findBySlug(slug);
    }

    @Test
    void createPost_ShouldSaveAndReturnPost() {
        BlogPost post = new BlogPost();
        when(blogPostRepository.save(post)).thenReturn(post);

        BlogPost result = blogService.createPost(post);

        assertNotNull(result);
        verify(blogPostRepository, times(1)).save(post);
    }
}
