package com.example.demo.domain.model.enums;

import java.util.Objects;

import com.example.demo.domain.model.responses.GenericResponseForChatGPT;
import com.example.demo.domain.model.responses.ResponseAdvancedForChatGPT;
import com.example.demo.domain.model.responses.ResponseMiddleForChatGPT;
import com.example.demo.domain.model.responses.ResponseSimpleForChatGPT;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TypeOfPDFs {
    MODELO_SIMPLES("modelo_pdf.simples", ResponseSimpleForChatGPT.class),
    MODELO_MEDIO("modelo_pdf.medio", ResponseMiddleForChatGPT.class),
    MODELO_AVANCADO("modelo.pdf.avancado", ResponseAdvancedForChatGPT.class);
    
    private final String value;
    private final Class<? extends GenericResponseForChatGPT> responseClass;

    public TypeOfPDFs toType(String value) {
        for(TypeOfPDFs type: TypeOfPDFs.values()) {
            if(Objects.equals(type.getValue(), value)) {
                return type;
            }
        }
        return null;
    }

    public GenericResponseForChatGPT createResponseInstance() {
        try {
            return (GenericResponseForChatGPT) responseClass.getMethod("of").invoke(null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create response instance  " + e.getMessage());
        }
    }
}
