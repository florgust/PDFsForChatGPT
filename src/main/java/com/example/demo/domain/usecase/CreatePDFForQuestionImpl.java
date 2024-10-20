package com.example.demo.domain.usecase;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.model.AnswerResponseModel;
import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.responses.GenericResponseForChatGPT;
import com.example.demo.domain.model.responses.ResponseMiddleForChatGPT;
import com.example.demo.domain.model.responses.ResponseSimpleForChatGPT;
import com.example.demo.domain.port.CreatePDF;
import com.example.demo.domain.port.SearchAnswerAPIChatGPT;
import com.example.demo.domain.resource.CreatePDFForQuestion;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePDFForQuestionImpl implements CreatePDFForQuestion {

    private final SearchAnswerAPIChatGPT searchAnswerAPIChatGPT;
    private final CreatePDF createPDF;

    @Override
    public MultipartFile createPDFForQuestion(QuestionModel question) {
        AnswerResponseModel answerResponseModel = searchAnswerAPIChatGPT.searchAnswerAPIChatGPT(question);
        return redirectToPDFTemplate(question, answerResponseModel);
    }


    private MultipartFile redirectToPDFTemplate(QuestionModel question, AnswerResponseModel answerResponseModel) {
        GenericResponseForChatGPT response = question.getTypeOfPDFs().createResponseInstance();
        if(response instanceof ResponseSimpleForChatGPT) {
            return buildingSimplePDF(answerResponseModel, question);
        } else if (response instanceof ResponseMiddleForChatGPT) {
            return buildingMediumPDF(answerResponseModel, question);
        } else return buildingAdvancedPDF(answerResponseModel, question);
    }

    private MultipartFile buildingSimplePDF(AnswerResponseModel answerResponseModel, QuestionModel questionModel) {
        return createPDF.createSimplePDF(answerResponseModel, questionModel);
    }

    private MultipartFile buildingMediumPDF(AnswerResponseModel answerResponseModel, QuestionModel questionModel) {
        return createPDF.createMediumPDF(answerResponseModel, questionModel);
    }

    private MultipartFile buildingAdvancedPDF(AnswerResponseModel answerResponseModel, QuestionModel questionModel) {
        return createPDF.createAdvancedPDF(answerResponseModel, questionModel);
    }
}
