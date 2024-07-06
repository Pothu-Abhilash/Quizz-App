package com.accioJob.QuizService.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class QuizRequest {

    String categoryName;
    Integer numQuestions;
    String title;
}
