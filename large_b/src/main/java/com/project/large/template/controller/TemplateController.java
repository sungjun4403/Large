package com.project.large.template.controller;


import com.project.large.template.dto.TemplateCreate;
import com.project.large.template.dto.TemplateEdit;
import com.project.large.template.entity.Template;
import com.project.large.template.repository.TemplateRepository;
import com.project.large.template.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TemplateController {
    private final TemplateService templateService;
    private final TemplateRepository templateRepository;

    //VIEW ONE
    @GetMapping("template/{gitID}/{templateID}")
    public Template viewOneByTemplateID(@PathVariable Long templateID, @PathVariable String gitID) {
        return templateRepository.findById(templateID).orElseThrow();

    }

    //VIEW ALL BY GITID
    @GetMapping("template/{gitID}")
    public List<Template> viewAllByGitID(@PathVariable String gitID) {
        return templateRepository.findByGitID(gitID);
    }

    //CREATE
    @PostMapping("template/create")
    public void create(@RequestBody TemplateCreate templateCreate) {
        templateService.create(templateCreate);
    }

    //EDIT
    @PatchMapping("template/{gitID}/{templateID}")
    public void edit (@PathVariable String gitID, @PathVariable Long templateID, @RequestBody TemplateEdit templateEdit) {
        templateService.edit(templateEdit, templateID);
    }

    //DELETE
    @DeleteMapping("template/{gitID}/{templateID}")
    public void delete (@PathVariable String gitID, @PathVariable Long templateID) {
        templateService.delete(templateID);
    }

    //CREATE
    @PostMapping("template/wholeModify/{gitID}")
    public void wholeModify(@PathVariable String gitID, @RequestBody List<LinkedHashMap<Object, Object>> wholeTemplate) {
        //Trim -> Add (create) 분리 -> delete -> check if modified -> edit
        List<LinkedHashMap<Object, Object>> trimmedWholeTemplate = templateService.trimWholeTemplate(wholeTemplate);
        templateService.divideCreateFromWhole(trimmedWholeTemplate, gitID);
    }


}

