package com.example.demo.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.responses.GenericResponseForChatGPT;
import com.example.demo.domain.resource.CreatePDFForQuestion;
import com.example.demo.infrastructure.response.AnswerResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class PdfForChatGPTController {
    
    private final CreatePDFForQuestion createPDFForQuestion;

    @GetMapping("/pdf-for-chat-gpt")
    public ResponseEntity<AnswerResponse> postPdfForChatGPT(@Valid @RequestBody QuestionModel quest) {
        GenericResponseForChatGPT genericResponseForChatGPT = createPDFForQuestion.createPDFForQuestion(quest);
        return ResponseEntity.ok().body(AnswerResponse.builder().answer(genericResponseForChatGPT.getAnswer()).build());
    }
    
}
