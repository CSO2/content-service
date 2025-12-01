package com.CSO2.contentservice.controller;

import com.CSO2.contentservice.model.BlogPost;
import com.CSO2.contentservice.model.FAQ;
import com.CSO2.contentservice.model.Testimonial;
import com.CSO2.contentservice.service.BlogService;
import com.CSO2.contentservice.service.FAQService;
import com.CSO2.contentservice.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private FAQService faqService;

    @Autowired
    private TestimonialService testimonialService;

    @GetMapping("/blog")
    public List<BlogPost> getAllBlogPosts(@RequestParam(required = false) Boolean featured) {
        if (featured != null && featured) {
            return blogService.getFeaturedPosts();
        }
        return blogService.getAllPublishedPosts();
    }

    @GetMapping("/blog/{slug}")
    public ResponseEntity<BlogPost> getBlogPostBySlug(@PathVariable String slug) {
        return blogService.getPostBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/blog/id/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable String id) {
        return blogService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/faq")
    public List<FAQ> getAllFAQs() {
        return faqService.getAllFAQs();
    }

    @GetMapping("/testimonials")
    public List<Testimonial> getAllTestimonials() {
        return testimonialService.getAllTestimonials();
    }

    @PostMapping("/testimonials")
    public ResponseEntity<Testimonial> createTestimonial(@RequestBody Testimonial testimonial) {
        return ResponseEntity.ok(testimonialService.createTestimonial(testimonial));
    }
}
