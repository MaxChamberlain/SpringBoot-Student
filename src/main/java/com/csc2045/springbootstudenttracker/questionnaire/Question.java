package com.csc2045.springbootstudenttracker.questionnaire;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Question {
    private String question;
    private String type;
    private Boolean isRequired;
    private String[] options;
    private int questionNumber;
}
