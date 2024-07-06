package com.accioJob.QuestionService.Repository;

import com.accioJob.QuestionService.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Questions ,Integer> {
    List<Questions> findByCategory(String category);

    @Query(value = "SELECT q.id FROM Questions q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(String category, int numQ);
}
