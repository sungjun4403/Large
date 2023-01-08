package com.project.large.template.repository;

import com.project.large.template.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    List<Template> findByGitID(String gitID);
    Optional<Template> findById(Long id);
    Optional<Template> findByGitIDAndTemplateNumber(String gitID, Integer templateNumber);
}
