package com.example.demo.infrastructure.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.resource.CreatePDFForQuestion;
import com.example.demo.infrastructure.request.QuestionRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PdfForChatGPTController {

    private final CreatePDFForQuestion createPDFForQuestion;

    @PostMapping("/pdf-for-chat-gpt")
    public ResponseEntity<byte[]> postPdfForChatGPT(@Valid @RequestBody QuestionRequest questionRequest,
            @RequestHeader @Min(1) @Max(3) int valueModelPdf) {
        MultipartFile genericResponseForChatGPT = createPDFForQuestion
                .createPDFForQuestion(questionRequest.toModel(valueModelPdf));
        log.info("Informações recebidas: {}", questionRequest);
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", genericResponseForChatGPT.getOriginalFilename());

            log.info("Tamanho do PDF gerado: {}", genericResponseForChatGPT.getSize());
            return new ResponseEntity<>(genericResponseForChatGPT.getBytes(), headers, HttpStatus.OK);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar PDF", e);
        }
    }

}
