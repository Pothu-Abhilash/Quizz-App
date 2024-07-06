package com.accioJob.QuizService.Feign;

import com.accioJob.QuizService.Request.ScoreResponse;
import com.accioJob.QuizService.Resoponse.QuestionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("QuestionService")

public interface QuizInterface {

    @GetMapping("question/generateQuiz")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category,
                                                      @RequestParam Integer noOfQuestions);

    @PostMapping("question/getQuestions")
    public ResponseEntity<List<QuestionResponse>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
    @PostMapping("/question/getScore")
    public Integer getScore(@RequestBody List<ScoreResponse> scoreResponseList);

}
