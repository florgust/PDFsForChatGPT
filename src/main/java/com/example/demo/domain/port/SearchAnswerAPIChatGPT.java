package com.example.demo.domain.port;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.responses.GenericResponseForChatGPT;

@Component
public interface SearchAnswerAPIChatGPT {
    GenericResponseForChatGPT searchAnswerAPIChatGPT(QuestionModel question);
}
