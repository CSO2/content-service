package com.CSO2.contentservice.controller;

import com.CSO2.contentservice.model.BlogPost;
import com.CSO2.contentservice.model.FAQ;
import com.CSO2.contentservice.model.Testimonial;
import com.CSO2.contentservice.service.BlogService;
import com.CSO2.contentservice.service.FAQService;
import com.CSO2.contentservice.service.TestimonialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ContentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BlogService blogService;

    @Mock
    private FAQService faqService;

    @Mock
    private TestimonialService testimonialService;

    @InjectMocks
    private ContentController contentController;

    private ObjectMapper objectMapper;

    private BlogPost blogPost;
    private FAQ faq;
    private Testimonial testimonial;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(contentController).build();

        blogPost = new BlogPost();
        blogPost.setId("1");
        blogPost.setTitle("Test Blog");
        blogPost.setSlug("test-blog");
        blogPost.setPublished(true);

        faq = new FAQ();
        faq.setId("1");
        faq.setQuestion("Question?");
        faq.setAnswer("Answer.");

        testimonial = new Testimonial();
        testimonial.setId("1");
        testimonial.setName("John Doe");
        testimonial.setText("Great service!");
    }

    @Test
    void getAllBlogPosts_ShouldReturnList() throws Exception {
        when(blogService.getAllPublishedPosts()).thenReturn(Arrays.asList(blogPost));

        mockMvc.perform(get("/api/content/blog"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].slug").value("test-blog"));
    }

    @Test
    void getAllBlogPosts_Featured_ShouldReturnList() throws Exception {
        when(blogService.getFeaturedPosts()).thenReturn(Arrays.asList(blogPost));

        mockMvc.perform(get("/api/content/blog").param("featured", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].slug").value("test-blog"));
    }

    @Test
    void getBlogPostBySlug_ShouldReturnPost_WhenFound() throws Exception {
        when(blogService.getPostBySlug("test-blog")).thenReturn(Optional.of(blogPost));

        mockMvc.perform(get("/api/content/blog/test-blog"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.slug").value("test-blog"));
    }

    @Test
    void getBlogPostBySlug_ShouldReturnNotFound_WhenNotFound() throws Exception {
        when(blogService.getPostBySlug("unknown")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/content/blog/unknown"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllFAQs_ShouldReturnList() throws Exception {
        when(faqService.getAllFAQs()).thenReturn(Arrays.asList(faq));

        mockMvc.perform(get("/api/content/faq"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].question").value("Question?"));
    }

    @Test
    void getAllTestimonials_ShouldReturnList() throws Exception {
        when(testimonialService.getAllTestimonials()).thenReturn(Arrays.asList(testimonial));

        mockMvc.perform(get("/api/content/testimonials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    void createTestimonial_ShouldReturnCreatedTestimonial() throws Exception {
        when(testimonialService.createTestimonial(any(Testimonial.class))).thenReturn(testimonial);

        mockMvc.perform(post("/api/content/testimonials")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testimonial)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
