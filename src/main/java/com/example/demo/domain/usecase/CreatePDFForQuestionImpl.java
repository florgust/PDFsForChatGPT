package com.example.demo.domain.usecase;

import org.springframework.stereotype.Service;

import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.responses.GenericResponseForChatGPT;
import com.example.demo.domain.port.SearchAnswerAPIChatGPT;
import com.example.demo.domain.resource.CreatePDFForQuestion;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePDFForQuestionImpl implements CreatePDFForQuestion {

    private final SearchAnswerAPIChatGPT searchAnswerAPIChatGPT;

    @Override
    public GenericResponseForChatGPT createPDFForQuestion(QuestionModel question) {
        return searchAnswerAPIChatGPT.searchAnswerAPIChatGPT(question);
    }

}
