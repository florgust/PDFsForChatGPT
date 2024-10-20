package com.example.demo.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.AnswerResponseModel;
import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.port.SearchAnswerAPIChatGPT;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchAnswerAPIChatGPTImpl implements SearchAnswerAPIChatGPT {

    @Autowired
    private final Environment env;

    @Override
    public AnswerResponseModel searchAnswerAPIChatGPT(QuestionModel question) {
        log.info(String.format("%s,%s", env.getProperty(question.getTypeOfPDFs().getValue()), question.toString()));
        return AnswerResponseModel.builder().answer(
                String.format("%s,%s", env.getProperty(question.getTypeOfPDFs().getValue()), question.getQuestion()))
                .build();
    }

}
