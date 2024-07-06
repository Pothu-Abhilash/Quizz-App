package com.accioJob.QuizService.Service;

import com.accioJob.QuizService.Feign.QuizInterface;
import com.accioJob.QuizService.Model.Quiz;
import com.accioJob.QuizService.Repository.QuizRepository;
import com.accioJob.QuizService.Request.QuizRequest;
import com.accioJob.QuizService.Request.ScoreResponse;
import com.accioJob.QuizService.Resoponse.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizInterface quizInterface;


    public ResponseEntity<String> createQuiz(String categoryName, Integer numQuestions, String title) {

        List<Integer> questions = quizInterface.generateQuiz(categoryName,numQuestions).getBody();

        Quiz quiz = new Quiz();

        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionResponse>> getQuizQuestions(Integer id) {

        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionResponses = quiz.getQuestionIds();
        ResponseEntity<List<QuestionResponse>> questions = quizInterface.getQuestionsFromId(questionResponses);


        return questions;
    }

    public Integer submitQuiz(Integer id, List<ScoreResponse> scoreResponse) {

        Integer totalScore = quizInterface.getScore(scoreResponse);
        return totalScore;
    }
}
