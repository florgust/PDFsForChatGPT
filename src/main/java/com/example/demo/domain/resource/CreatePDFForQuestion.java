package com.example.demo.domain.resource;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.responses.GenericResponseForChatGPT;

@Component
public interface CreatePDFForQuestion {
    public GenericResponseForChatGPT createPDFForQuestion(QuestionModel question);
}
