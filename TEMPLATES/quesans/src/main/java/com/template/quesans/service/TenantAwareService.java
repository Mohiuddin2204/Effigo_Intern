package com.template.quesans.service;

import com.template.quesans.config.TenantContext;
import com.template.quesans.entities.DefaultAnswer;
import com.template.quesans.entities.Question;
import com.template.quesans.entities.Template;

public abstract class TenantAwareService {

    protected void setTenantToTemplate(Template template) {
        template.setTenantId(TenantContext.getTenantId());
    }

    protected void setTenantToQuestion(Question question) {
        question.setTenantId(TenantContext.getTenantId());
    }

    protected void setTenantToDefaultAnswer(DefaultAnswer answer) {
        answer.setTenantId(TenantContext.getTenantId());
    }
}