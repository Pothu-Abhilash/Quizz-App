package com.accioJob.QuizService.Resoponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private Integer Id;
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
