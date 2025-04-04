package com.template.quesans.controller;

import com.template.quesans.entities.DefaultAnswer;
import com.template.quesans.service.DefaultAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/answers")
public class DefaultAnswerController {
    @Autowired
    private DefaultAnswerService defaultAnswerService;

    @PostMapping
    public DefaultAnswer createDefaultAnswer(@RequestBody DefaultAnswer answer) {
        return defaultAnswerService.createDefaultAnswer(answer);
    }

    @GetMapping
    public List<DefaultAnswer> getAllAnswers() {
        return defaultAnswerService.getAllDefaultAnswers();
    }

    @GetMapping("/{id}")
    public DefaultAnswer getAnswerById(@PathVariable Long id) {
        return defaultAnswerService.getDefaultAnswerById(id);
    }

    @PostMapping("/bulk")
    public List<DefaultAnswer> createDefaultAnswersBulk(@RequestBody List<DefaultAnswer> answers) {
        return defaultAnswerService.createDefaultAnswersBulk(answers);
    }
}