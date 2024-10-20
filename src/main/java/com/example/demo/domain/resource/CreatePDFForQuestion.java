package com.example.demo.domain.resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.model.QuestionModel;

@Component
public interface CreatePDFForQuestion {
    public MultipartFile createPDFForQuestion(QuestionModel question);
}
