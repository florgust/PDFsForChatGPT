package com.example.demo.infrastructure.adapter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.model.AnswerResponseModel;
import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.port.CreatePDF;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;


@Service
public class CreatePDFImpl implements CreatePDF {

    @Override
    public MultipartFile createSimplePDF(AnswerResponseModel answerResponseModel, QuestionModel questionModel) {
        try {
            // Geração do PDF em memória (ByteArrayOutputStream)
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Adicionando o Título em negrito
            Text titulo = new Text(questionModel.getQuestion()).setBold().setFontSize(16);
            Paragraph paragraphTitulo = new Paragraph().add(titulo)
                .setTextAlignment(TextAlignment.LEFT);
            document.add(paragraphTitulo);

            // Nome e Curso
            Paragraph nomeCurso = new Paragraph(String.format("%s\n%s", questionModel.getName(), questionModel.getCourse()))
                .setTextAlignment(TextAlignment.LEFT);
            document.add(nomeCurso);

            // Data (à direita)
            Paragraph data = new Paragraph(questionModel.getDate())
                .setTextAlignment(TextAlignment.RIGHT);
            document.add(data);

            // Texto formatado e justificado
            Paragraph response = new Paragraph("Response: formatado e justificado\n- \n- \n- \n- \n- \n- \n- \n-")
                .setTextAlignment(TextAlignment.JUSTIFIED);
            document.add(response);

            // Fechar o documento
            document.close();

            // Converter o ByteArrayOutputStream para MultipartFile
            ByteArrayInputStream inputStream = new ByteArrayInputStream(baos.toByteArray());
            MultipartFile multipartFile = new MockMultipartFile("documento.pdf", "documento.pdf", "application/pdf",
                    inputStream);

            return multipartFile;
        } catch (Exception ex) {
            throw new RuntimeException("Erro", ex);
        }
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
