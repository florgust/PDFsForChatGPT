package com.example.demo.domain.model.enums;

import java.util.Objects;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TypeOfPDFs {
    MODELO_SIMPLES("modelo_pdf.simples"),
    MODELO_MEDIO("modelo_pdf.medio"),
    MODELO_AVANCADO("modelo.pdf.avancado");
    
    private final String value;

    public TypeOfPDFs toType(String value) {
        for(TypeOfPDFs type: TypeOfPDFs.values()) {
            if(Objects.equals(type.getValue(), value)) {
                return type;
            }
        }
        return null;
    }
}
