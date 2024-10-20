package com.example.demo.domain.model;

import com.example.demo.domain.model.enums.TypeOfPDFs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionModel {
    private String question;
    private String name;
    private String course;
    private String date;
    private TypeOfPDFs typeOfPDFs;
}
