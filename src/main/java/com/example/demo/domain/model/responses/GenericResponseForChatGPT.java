package com.example.demo.domain.model.responses;

import lombok.Data;

@Data
public abstract class GenericResponseForChatGPT {
    private String title;
    private String name;
    private String answer;
    private String course;
    private String date;

    abstract String testes();
}
