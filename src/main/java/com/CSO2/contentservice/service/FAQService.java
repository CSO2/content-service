package com.CSO2.contentservice.service;

import com.CSO2.contentservice.model.FAQ;
import com.CSO2.contentservice.repository.FAQRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FAQService {

    private final FAQRepository faqRepository;

    @Autowired
    public FAQService(FAQRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public List<FAQ> getAllFAQs() {
        return faqRepository.findAll();
    }

    public FAQ createFAQ(FAQ faq) {
        return faqRepository.save(faq);
    }
}
