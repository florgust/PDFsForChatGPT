package com.example.demo.domain.model.responses;

import java.util.List;

import lombok.Data;

@Data
public abstract class GenericResponseForChatGPT {
    private String title;
    private String description;
    private List<String> characteristics;
    private String conclusion;
}
