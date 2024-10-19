package com.example.demo.infrastructure.request;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.model.enums.TypeOfPDFs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {
    @NotEmpty(message = "A pergunta não pode estar nula")
    private String question;
    private TypeOfPDFs typeOfPDFs;

    public QuestionModel toModel(int valueModelpdf) {
        if(valueModelpdf == 1) {
            this.typeOfPDFs = TypeOfPDFs.MODELO_SIMPLES;
        } else if (valueModelpdf == 2){
            this.typeOfPDFs = TypeOfPDFs.MODELO_MEDIO;
        } else this.typeOfPDFs = TypeOfPDFs.MODELO_AVANCADO;

        QuestionModel questionModel = new QuestionModel();
        try {
            BeanUtils.copyProperties(questionModel, this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Error copying properties", e);
        }
        return questionModel;
    }
}
