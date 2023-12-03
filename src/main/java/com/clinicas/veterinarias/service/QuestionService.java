package com.clinicas.veterinarias.service;

import com.clinicas.veterinarias.entity.Question;

import java.util.List;

public interface QuestionService {
    Question createQuestion(Question question);
    Question updateQuestion(Long questionId, Question updatedQuestion);
    void deleteQuestion(Long questionId);
    List<Question> getAllQuestions();
}
