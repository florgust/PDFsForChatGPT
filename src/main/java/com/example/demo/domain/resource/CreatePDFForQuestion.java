package com.example.demo.domain.resource;

import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.responses.GenericResponseForChatGPT;

public interface CreatePDFForQuestion {
    public GenericResponseForChatGPT createPDFForQuestion(QuestionModel question);
}
