package com.example.demo.infrastructure.adapter;

import org.springframework.stereotype.Service;

import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.responses.GenericResponseForChatGPT;
import com.example.demo.domain.port.SearchAnswerAPIChatGPT;

@Service
public class SearchAnswerAPIChatGPTImpl implements SearchAnswerAPIChatGPT {

    @Override
    public GenericResponseForChatGPT searchAnswerAPIChatGPT(QuestionModel question) {
        return GenericResponseForChatGPT.builder().answer("Answer for question: " + question.getQuestion()).build();
    }

}
