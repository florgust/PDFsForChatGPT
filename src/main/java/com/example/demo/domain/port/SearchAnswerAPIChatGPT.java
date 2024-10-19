package com.example.demo.domain.port;

import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.responses.GenericResponseForChatGPT;

public interface SearchAnswerAPIChatGPT {
    GenericResponseForChatGPT searchAnswerAPIChatGPT(QuestionModel question);
}
