package com.accioJob.QuestionService.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class scoreRequest {

    private int score;
    private int questionid;
    private String answer;
}
