package com.CSO2.contentservice.service;

import com.CSO2.contentservice.model.FAQ;
import com.CSO2.contentservice.repository.FAQRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FAQServiceTest {

    @Mock
    private FAQRepository faqRepository;

    @InjectMocks
    private FAQService faqService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllFAQs_ShouldReturnListOfFAQs() {
        FAQ faq1 = new FAQ();
        FAQ faq2 = new FAQ();
        when(faqRepository.findAll()).thenReturn(Arrays.asList(faq1, faq2));

        List<FAQ> result = faqService.getAllFAQs();

        assertEquals(2, result.size());
        verify(faqRepository, times(1)).findAll();
    }

    @Test
    void createFAQ_ShouldSaveAndReturnFAQ() {
        FAQ faq = new FAQ();
        when(faqRepository.save(faq)).thenReturn(faq);

        FAQ result = faqService.createFAQ(faq);

        assertNotNull(result);
        verify(faqRepository, times(1)).save(faq);
    }
}
