package com.template.quesans.controller;

import com.template.quesans.entities.Question;
import com.template.quesans.entities.Template;
import com.template.quesans.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/templates")
public class TemplateController {
    @Autowired
    private TemplateService templateService;

    @PostMapping
    public Template createTemplate(@RequestBody Template template) {
        return templateService.createTemplate(template);
    }

    @GetMapping
    public List<Template> getAllTemplates() {
        return templateService.getAllTemplates();
    }

    @GetMapping("/{id}")
    public Template getTemplateById(@PathVariable Long id) {
        return templateService.getTemplateById(id);
    }

    @PutMapping("/{id}")
    public Template updateTemplate(@PathVariable Long id, @RequestBody Template updatedTemplate) {
        return templateService.updateTemplate(id, updatedTemplate);
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable Long id) {
        templateService.deleteTemplate(id);
    }

    @GetMapping("/{id}/questions")
    public List<Question> getQuestionsByTemplateId(@PathVariable Long id) {
        Template template = templateService.getTemplateById(id);
        return template.getQuestions();  // Return associated questions
    }

    @PostMapping("/{id}/questions/bulk")
    public Template addQuestionsToTemplate(@PathVariable Long id, @RequestBody List<Question> questions) {
        return templateService.addQuestionsToTemplate(id, questions);
    }

    @PostMapping("/{id}/quesans/bulk")
    public ResponseEntity<String> addQuestionsWithAnswersToTemplate(
            @PathVariable Long id,
            @RequestBody List<Question> questions) {
        templateService.addQuestionsWithAnswers(id, questions);
        return ResponseEntity.ok("Questions and answers added successfully!");
    }
}