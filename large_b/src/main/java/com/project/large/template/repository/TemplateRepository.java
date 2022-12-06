package com.project.large.template.repository;

import com.project.large.template.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    List<Template> findByGitID(String gitID);
}
