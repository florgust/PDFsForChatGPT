package com.example.demo.domain.model.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponseForChatGPT {
    private String title;
    private String name;
    private String answer;
    private String course;
    private String date;
}
