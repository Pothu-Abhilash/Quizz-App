package com.accioJob.QuizService.Controller;

import com.accioJob.QuizService.Request.QuizRequest;
import com.accioJob.QuizService.Request.ScoreResponse;
import com.accioJob.QuizService.Resoponse.QuestionResponse;
import com.accioJob.QuizService.Service.QuizService;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("createQuiz")
    public ResponseEntity<String> createQuiz(@RequestBody QuizRequest quizRequest){

        return quizService.createQuiz(quizRequest.getCategoryName(),quizRequest.getNumQuestions(),quizRequest.getTitle());
    }

    @GetMapping ("get/{id}")
    public ResponseEntity<List<QuestionResponse>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public Integer submitQuiz(@PathVariable Integer id, @RequestBody List<ScoreResponse> scoreResponse){
        return quizService.submitQuiz(id,  scoreResponse);
    }
}
