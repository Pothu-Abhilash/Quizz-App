package com.accioJob.QuestionService.Controller;

import com.accioJob.QuestionService.Model.Questions;
import com.accioJob.QuestionService.Request.scoreRequest;
import com.accioJob.QuestionService.Response.QuestionResponse;
import com.accioJob.QuestionService.Response.ScoreResponse;
import com.accioJob.QuestionService.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions(){

        try{
            return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category){

        try {
            return new ResponseEntity<>(questionService.getQuestionByCategory(category),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Questions questions){
        try {
            questionService.addQuestion(questions);
            return new ResponseEntity<>("Question added to db successfully ",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("generateQuiz")
    public ResponseEntity<List<Integer>> generateQuiz(@RequestParam String category,
                                                        @RequestParam Integer noOfQuestions){

        try {
            return new ResponseEntity<>(questionService.generateQuiz(category,noOfQuestions), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("getQuestions")
    public ResponseEntity getQuestionsFromId(@RequestBody List<Integer> questionIds){

        try {
            return new ResponseEntity<>(questionService.getQuestionsFormId(questionIds),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }


    }
    @PostMapping("/getScore")
    public Integer getScore(@RequestBody List<ScoreResponse> scoreResponseList){


        return (questionService.getScore(scoreResponseList));

    }
}
