package com.example.demo.domain.port;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.model.AnswerResponseModel;
import com.example.demo.domain.model.QuestionModel;

@Component
public interface CreatePDF {
    MultipartFile createSimplePDF(AnswerResponseModel answerResponseModel, QuestionModel questionModel);
    MultipartFile createMediumPDF(AnswerResponseModel answerResponseModel, QuestionModel questionModel);
    MultipartFile createAdvancedPDF(AnswerResponseModel answerResponseModel, QuestionModel questionModel);
}
