package com.template.quesans.service;

import com.template.quesans.config.TenantContext;
import com.template.quesans.entities.DefaultAnswer;
import com.template.quesans.entities.Question;
import com.template.quesans.repos.DefaultAnswerRepository;
import com.template.quesans.repos.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService extends TenantAwareService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private DefaultAnswerRepository defaultAnswerRepository;

    public Question createQuestion(Question question) {
        setTenantToQuestion(question);
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findByTenantId(TenantContext.getTenantId());
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElse(null);
    }

    public Question updateQuestion(Long id, Question updatedQuestion) {
        return questionRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).map(existingQuestion -> {
            existingQuestion.setQuestionName(updatedQuestion.getQuestionName());
            existingQuestion.setStatus(updatedQuestion.getStatus());
            existingQuestion.setCreatedBy(updatedQuestion.getCreatedBy());
            return questionRepository.save(existingQuestion);
        }).orElse(null);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteByIdAndTenantId(id, TenantContext.getTenantId());
    }

    @Transactional
    public List<Question> createQuestionsWithDefaultAnswers(List<Question> questionsWithAnswers) {
        List<Question> savedQuestions = new ArrayList<>();

        for (Question question : questionsWithAnswers) {
            DefaultAnswer answer = question.getDefaultAnswer();
            question.setDefaultAnswer(null); // Temporarily remove to avoid circular save

            setTenantToQuestion(question);
            Question savedQuestion = questionRepository.save(question);

            if (answer != null) {
                answer.setQuestion(savedQuestion);
                answer.setStatus(savedQuestion.getStatus());
                answer.setCreatedBy(savedQuestion.getCreatedBy());
                setTenantToDefaultAnswer(answer);
                savedQuestion.setDefaultAnswer(defaultAnswerRepository.save(answer));
            }

            savedQuestions.add(savedQuestion);
        }

        return savedQuestions;
    }
}
