package com.example.demo.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.responses.GenericResponseForChatGPT;
import com.example.demo.domain.port.SearchAnswerAPIChatGPT;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SearchAnswerAPIChatGPTImpl implements SearchAnswerAPIChatGPT {

    @Autowired
    private Environment env;

    @Override
    public GenericResponseForChatGPT searchAnswerAPIChatGPT(QuestionModel question) {
        log.info(env.getProperty(question.getTypeOfPDFs().getValue()));
        return GenericResponseForChatGPT.builder().answer("Answer for question: " + question.getQuestion()).build();
    }

}
