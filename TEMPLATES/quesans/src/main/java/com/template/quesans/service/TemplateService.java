package com.template.quesans.service;

import com.template.quesans.config.TenantContext;
import com.template.quesans.entities.DefaultAnswer;
import com.template.quesans.entities.Question;
import com.template.quesans.entities.Template;
import com.template.quesans.repos.QuestionRepository;
import com.template.quesans.repos.TemplateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateService extends TenantAwareService {
    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private QuestionRepository questionRepository;

    public Template createTemplate(Template template) {
        setTenantToTemplate(template);
        return templateRepository.save(template);
    }

    public List<Template> getAllTemplates() {
        return templateRepository.findByTenantId(TenantContext.getTenantId());
    }

    public Template getTemplateById(Long id) {
        return templateRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).orElse(null);
    }

    public Template updateTemplate(Long id, Template updatedTemplate) {
        return templateRepository.findByIdAndTenantId(id, TenantContext.getTenantId()).map(existingTemplate -> {
            existingTemplate.setTemplateName(updatedTemplate.getTemplateName());
            existingTemplate.setStatus(updatedTemplate.getStatus());
            existingTemplate.setCreatedBy(updatedTemplate.getCreatedBy());
            return templateRepository.save(existingTemplate);
        }).orElse(null);
    }

    public void deleteTemplate(Long id) {
        templateRepository.deleteByIdAndTenantId(id, TenantContext.getTenantId());
    }

    @Transactional
    public Template addQuestionsToTemplate(Long templateId, List<Question> questions) {
        return templateRepository.findByIdAndTenantId(templateId, TenantContext.getTenantId()).map(template -> {
            for (Question question : questions) {
                question.setTemplate(template);
                question.setStatus(template.getStatus());
                question.setCreatedBy(template.getCreatedBy());
                setTenantToQuestion(question);
            }
            template.getQuestions().addAll(questions);
            return templateRepository.save(template);
        }).orElse(null);
    }

    @Transactional
    public void addQuestionsWithAnswers(Long templateId, List<Question> questions) {
        Template template = templateRepository.findByIdAndTenantId(templateId, TenantContext.getTenantId())
                .orElseThrow(() -> new RuntimeException("Template not found"));

        for (Question question : questions) {
            question.setTemplate(template);
            setTenantToQuestion(question);

            DefaultAnswer defaultAnswer = question.getDefaultAnswer();
            if (defaultAnswer != null) {
                defaultAnswer.setQuestion(question);
                setTenantToDefaultAnswer(defaultAnswer);
            }
        }

        questionRepository.saveAll(questions);
    }
}