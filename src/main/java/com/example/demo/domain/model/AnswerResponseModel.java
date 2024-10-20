package com.example.demo.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerResponseModel {
    private String answer;
}
