package com.accioJob.QuestionService.Service;

import com.accioJob.QuestionService.Model.Questions;
import com.accioJob.QuestionService.Repository.QuestionRepository;
import com.accioJob.QuestionService.Request.scoreRequest;
import com.accioJob.QuestionService.Response.QuestionResponse;
import com.accioJob.QuestionService.Response.ScoreResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Questions> getAllQuestions() {

        List<Questions> questionsList = questionRepository.findAll();
        return questionsList;
    }

    public List<Questions> getQuestionByCategory(String category) {

        List<Questions>questionsList = questionRepository.findByCategory(category);
        return questionsList;
    }

    public Object addQuestion(Questions questions) {

        return questionRepository.save(questions);
    }

    public List<Integer> generateQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionRepository.findRandomQuestionsByCategory(categoryName, numQuestions);
        return questions;
    }

    public List<QuestionResponse> getQuestionsFormId(List<Integer> questionIds) throws Exception{
        List<Questions> questionsList = new ArrayList<>();
        List<QuestionResponse> questionResponseList = new ArrayList<>();

        for(Integer id : questionIds){
            Optional<Questions> question = questionRepository.findById(id);
            if(question.isEmpty()){
                throw new Exception("The Entered QuestionId "+id +" does not exists in DB. Please enter the correct QuestionId's");
            }
            questionsList.add(questionRepository.findById(id).get());
        }

        for(Questions question : questionsList){
            QuestionResponse questionResponse = new QuestionResponse();
            questionResponse.setId(question.getId());
            questionResponse.setQuestionTitle(question.getQuestionTitle());
            questionResponse.setOption1(question.getOption1());
            questionResponse.setOption2(question.getOption2());
            questionResponse.setOption3(question.getOption3());
            questionResponse.setOption4(question.getOption4());

            questionResponseList.add(questionResponse);
        }
        return questionResponseList;
    }


    public Integer getScore( List<ScoreResponse> scoreResponseList) {

        int answer = 0;
        for(ScoreResponse response : scoreResponseList){
            Questions question = questionRepository.findById(response.getQuestionid()).get();
            if(response.getAnswer().equals(question.getRightAnswer())){
                answer++;
            }
        }
        return answer;
    }
}
