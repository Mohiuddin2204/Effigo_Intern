package com.template.quesans.repos;

import com.template.quesans.entities.DefaultAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DefaultAnswerRepository extends JpaRepository<DefaultAnswer, Long> {
    List<DefaultAnswer> findByTenantId(String tenantId);
    Optional<DefaultAnswer> findByIdAndTenantId(Long id, String tenantId);
    void deleteByIdAndTenantId(Long id, String tenantId);
}