package com.example.demo.domain.port;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.AnswerResponseModel;
import com.example.demo.domain.model.QuestionModel;

@Component
public interface SearchAnswerAPIChatGPT {
    AnswerResponseModel searchAnswerAPIChatGPT(QuestionModel question);
}
