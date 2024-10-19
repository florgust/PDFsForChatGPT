package com.example.demo.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.demo.domain.model.responses.GenericResponseForChatGPT;
import com.example.demo.domain.resource.CreatePDFForQuestion;
import com.example.demo.infrastructure.request.QuestionRequest;
import com.example.demo.infrastructure.response.AnswerResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PdfForChatGPTController {

    private final CreatePDFForQuestion createPDFForQuestion;

    @GetMapping("/pdf-for-chat-gpt")
    public ResponseEntity<AnswerResponse> postPdfForChatGPT(@Valid @RequestBody QuestionRequest questionRequest,
            @RequestHeader @Min(1) @Max(3) int valueModelPdf) {
        GenericResponseForChatGPT genericResponseForChatGPT = createPDFForQuestion
                .createPDFForQuestion(questionRequest.toModel(valueModelPdf));
        return ResponseEntity.ok().body(AnswerResponse.builder().answer(genericResponseForChatGPT.getAnswer()).build());
    }

}
