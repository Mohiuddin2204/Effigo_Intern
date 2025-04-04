package com.template.quesans.service;

import com.template.quesans.config.TenantContext;
import com.template.quesans.entities.DefaultAnswer;
import com.template.quesans.entities.Question;
import com.template.quesans.repos.DefaultAnswerRepository;
import com.template.quesans.repos.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultAnswerService extends TenantAwareService {
    @Autowired
    private DefaultAnswerRepository defaultAnswerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public DefaultAnswer createDefaultAnswer(DefaultAnswer answer) {
        Question question = questionRepository.findByIdAndTenantId(
                answer.getQuestion().getId(), TenantContext.getTenantId()).orElse(null);

        if (question != null) {
            answer.setStatus(question.getStatus());
            answer.setCreatedBy(question.getCreatedBy());
            setTenantToDefaultAnswer(answer);
        }
        return defaultAnswerRepository.save(answer);
    }

    public List<DefaultAnswer> getAllDefaultAnswers() {
        return defaultAnswerRepository.findByTenantId(TenantContext.getTenantId());
    }

    public DefaultAnswer getDefaultAnswerById(Long id) {
        return defaultAnswerRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElse(null);
    }

    @Transactional
    public List<DefaultAnswer> createDefaultAnswersBulk(List<DefaultAnswer> answers) {
        for (DefaultAnswer answer : answers) {
            Question question = questionRepository.findByIdAndTenantId(
                    answer.getQuestion().getId(), TenantContext.getTenantId()).orElse(null);

            if (question != null) {
                answer.setStatus(question.getStatus());
                answer.setCreatedBy(question.getCreatedBy());
                setTenantToDefaultAnswer(answer);
            }
        }
        return defaultAnswerRepository.saveAll(answers);
    }
}