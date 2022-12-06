package com.project.large.template.service;

import com.project.large.template.dto.TemplateCreate;
import com.project.large.template.entity.Template;
import com.project.large.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;

    public void Create (TemplateCreate templateCreate) {

        Template template = Template.builder()
                .gitID(templateCreate.getGitID())
                .templateName(templateCreate.getTemplateName())
                .templateNumber(templateCreate.getTemplateNumber())
                .template(templateCreate.getTemplate())
                .hotKey(templateCreate.getHotKey())
                .build();

        templateRepository.save(template);
    }
}
