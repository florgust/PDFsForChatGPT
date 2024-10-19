package com.example.demo.domain.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionModel {
    @NotEmpty
    private final String question;
}
