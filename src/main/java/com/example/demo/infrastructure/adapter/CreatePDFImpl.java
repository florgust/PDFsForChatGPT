package com.example.demo.infrastructure.adapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.model.AnswerResponseModel;
import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.responses.ResponseSimpleForChatGPT;
import com.example.demo.domain.port.CreatePDF;
import com.itextpdf.html2pdf.HtmlConverter;

@Service
public class CreatePDFImpl implements CreatePDF {

    @Override
    public MultipartFile createSimplePDF(AnswerResponseModel answerResponseModel, QuestionModel questionModel) {
        try {
            ResponseSimpleForChatGPT responseSimple = createSimpleModel(answerResponseModel, questionModel);

            StringBuilder htmlBuilder = new StringBuilder();
            htmlBuilder.append("<html><head><title>Documento</title>")
                    .append("<style>")
                    .append("body { font-family: 'Times New Roman'; line-height: 1.5; margin: 20px; }")
                    .append("h1 { margin-bottom: 5px; }")
                    .append("h2 { line-height: 1.5; }")
                    .append("p { margin: 0; }")
                    .append(".date { text-align:right; margin-top: -15px; }")
                    .append(".spacing { margin-top: 20px; }")
                    .append("</style></head><body>");

            htmlBuilder.append("<h1>").append(responseSimple.getTitle()).append("</h1>");

            htmlBuilder.append("<p class='date'>").append(questionModel.getDate()).append("</p>");

            htmlBuilder.append("<p>").append(questionModel.getName()).append("<br>")
                    .append(questionModel.getCourse()).append("</p>");

            htmlBuilder.append("<p class='spacing' style='text-align:justify;'>")
                    .append(responseSimple.getDescription()).append("</p>");

            htmlBuilder.append("<h2>Características:</h2><ul>");
            for (String characteristic : responseSimple.getCharacteristics()) {
                htmlBuilder.append("<li>").append(characteristic).append("</li>");
            }
            htmlBuilder.append("</ul>");

            htmlBuilder.append("<p>").append(responseSimple.getConclusion()).append("</p>");

            htmlBuilder.append("</body></html>");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(htmlBuilder.toString(), baos);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
            MultipartFile multipartFile = new MockMultipartFile("documento.pdf", "documento.pdf", "application/pdf",
                    inputStream);

            return multipartFile;
        } catch (Exception ex) {
            throw new RuntimeException("Erro", ex);
        }
    }

    private ResponseSimpleForChatGPT createSimpleModel(AnswerResponseModel answerResponseModel,
            QuestionModel questionModel) {
        org.jsoup.nodes.Document doc = Jsoup.parse(answerResponseModel.getAnswer());
        ResponseSimpleForChatGPT response = ResponseSimpleForChatGPT.of();

        Elements elements = doc.body().children();

        List<String> paragraphs = new ArrayList<>();
        List<String> characteristics = new ArrayList<>();
        String title = "Título não disponível";
        String conclusion = "Conclusão não disponível";

        for (Element element : elements) {
            if (element.tagName().equals("h1")) {
                title = element.text();
            } else if (element.tagName().equals("p")) {
                paragraphs.add(element.text());
            } else if (element.tagName().equals("ul")) {
                Elements liElements = element.select("li");
                for (Element li : liElements) {
                    characteristics.add(li.text());
                }
            } else if (element.tagName().equals("h2")) {
            }
        }

        response.setTitle(title);

        if (!paragraphs.isEmpty()) {
            StringBuilder descriptionBuilder = new StringBuilder();
            for (int i = 0; i < paragraphs.size() - 1; i++) {
                descriptionBuilder.append(paragraphs.get(i)).append("\n");
            }
            response.setDescription(descriptionBuilder.toString().trim());
            conclusion = paragraphs.get(paragraphs.size() - 1);
        }

        response.setCharacteristics(
                characteristics.isEmpty() ? List.of("Nenhuma característica disponível") : characteristics);
        response.setConclusion(conclusion);

        return response;
    }

    @Override
    public MultipartFile createMediumPDF(AnswerResponseModel answerResponseModel, QuestionModel questionModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createMediumPDF'");
    }

    @Override
    public MultipartFile createAdvancedPDF(AnswerResponseModel answerResponseModel, QuestionModel questionModel) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createAdvancedPDF'");
    }

}
