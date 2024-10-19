package com.example.demo.infrastructure.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AnswerResponse {
    private String answer;
}
