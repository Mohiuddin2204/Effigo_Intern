package com.template.quesans.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class TenantResolver {

    public static final String DEFAULT_TENANT_ID = "tenant1";
    private static final String TENANT_HEADER = "X-TenantID";

    public String resolveTenantId(HttpServletRequest request) {
        String tenantId = request.getHeader(TENANT_HEADER);
        return (tenantId != null && !tenantId.isEmpty()) ? tenantId : DEFAULT_TENANT_ID;
    }
}