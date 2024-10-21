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
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

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

            // Criar uma tabela com duas colunas
            Table table = new Table(2);
            table.setWidth(UnitValue.createPercentValue(100)); // Definindo a largura da tabela para 100%

            // Adicionando o Título em negrito à primeira coluna
            Text titulo = new Text(questionModel.getQuestion()).setBold().setFontSize(16);
            Paragraph paragraphTitulo = new Paragraph().add(titulo)
                    .setTextAlignment(TextAlignment.LEFT);
            Cell cellTitulo = new Cell().add(paragraphTitulo).setBorder(null);
            table.addCell(cellTitulo);

            // Adicionando a data à segunda coluna, alinhada à direita
            Paragraph paragraphData = new Paragraph(questionModel.getDate())
                    .setTextAlignment(TextAlignment.RIGHT);
            Cell cellData = new Cell().add(paragraphData).setBorder(null);
            table.addCell(cellData);

            // Adicionando a tabela ao documento
            document.add(table);

            // Nome e Curso
            Paragraph nomeCurso = new Paragraph(
                    String.format("%s\n%s\n\n", questionModel.getName(), questionModel.getCourse()))
                    .setTextAlignment(TextAlignment.LEFT);
            document.add(nomeCurso);

            // Texto formatado e justificado
            Paragraph response = new Paragraph(answerResponseModel.getAnswer())
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
