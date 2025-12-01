package com.CSO2.contentservice.service;

import com.CSO2.contentservice.model.Testimonial;
import com.CSO2.contentservice.repository.TestimonialRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestimonialServiceTest {

    @Mock
    private TestimonialRepository testimonialRepository;

    @InjectMocks
    private TestimonialService testimonialService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTestimonials_ShouldReturnListOfTestimonials() {
        Testimonial t1 = new Testimonial();
        Testimonial t2 = new Testimonial();
        when(testimonialRepository.findAll()).thenReturn(Arrays.asList(t1, t2));

        List<Testimonial> result = testimonialService.getAllTestimonials();

        assertEquals(2, result.size());
        verify(testimonialRepository, times(1)).findAll();
    }

    @Test
    void createTestimonial_ShouldSaveAndReturnTestimonial() {
        Testimonial t = new Testimonial();
        when(testimonialRepository.save(t)).thenReturn(t);

        Testimonial result = testimonialService.createTestimonial(t);

        assertNotNull(result);
        verify(testimonialRepository, times(1)).save(t);
    }
}
