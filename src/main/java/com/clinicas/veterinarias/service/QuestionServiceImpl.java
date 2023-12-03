package com.clinicas.veterinarias.service;

import com.clinicas.veterinarias.entity.Question;
import com.clinicas.veterinarias.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Long questionId, Question updatedQuestion) {
        Optional<Question> existingQuestionOptional = questionRepository.findById(questionId);
        if (existingQuestionOptional.isPresent()) {
            Question existingQuestion = existingQuestionOptional.get();
            existingQuestion.setContent(updatedQuestion.getContent());
            // Puedes agregar más campos para actualizar según tu modelo
            return questionRepository.save(existingQuestion);
        }
        return null; // O manejar este caso según tu lógica de negocio
    }

    @Override
    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
