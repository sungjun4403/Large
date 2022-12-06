package com.project.large.template.controller;


import com.project.large.template.dto.TemplateCreate;
import com.project.large.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService;

    @PostMapping("template/create")
    public void templateCreate(@RequestBody TemplateCreate templateCreate) {
        templateService.Create(templateCreate);
    }
}
