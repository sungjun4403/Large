package com.project.large.template.service;

import com.project.large.global.utils.SecurityUtil;
import com.project.large.template.dto.TemplateCreate;
import com.project.large.template.dto.TemplateEdit;
import com.project.large.template.dto.TemplateEditor;
import com.project.large.template.entity.Template;
import com.project.large.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;


@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;

    //CREATE
    public void create (TemplateCreate templateCreate) {

        Template template = Template.builder()
                .gitID(templateCreate.getGitID())
                .templateName(templateCreate.getTemplateName())
                .templateNumber(templateCreate.getTemplateNumber())
                .template(templateCreate.getTemplate())
                .hotKey(templateCreate.getHotKey())
                .build();

        templateRepository.save(template);
    }

    //EDIT
    @Transactional
    public void edit (TemplateEdit templateEdit, String gitID, Long templateID) {
        String extractedGitID = SecurityUtil.getLoginedUserGitId();
//        if (extractedGitID.equals(gitID)) {
//
//        }

        System.out.println(extractedGitID);
        Template template = templateRepository.findById(templateID).orElseThrow();

        TemplateEditor.TemplateEditorBuilder editorBuilder = template.toEditor();

        editorBuilder.template(templateEdit.getGitID()); //in editable

        if (templateEdit.getTemplateName() != null) {
            editorBuilder.templateName(templateEdit.getTemplateName());
        }
        if (templateEdit.getTemplateNumber() != null) {
            editorBuilder.templateNumber(templateEdit.getTemplateNumber());
        }
        if (templateEdit.getTemplate() != null) {
            editorBuilder.template(templateEdit.getTemplate());
        }
        if (templateEdit.getHotKey() != null) {
            editorBuilder.hotKey(templateEdit.getHotKey());
        }

        template.edit(editorBuilder.build());
    }


    //DELETE
    public void delete (Long id) {
        Template template = templateRepository.findById(id).orElseThrow();
        templateRepository.delete(template);
    }

    public void MapWholeTemplateList(List<LinkedHashMap<Object, Object>> wholeTemplate) {
        System.out.println(wholeTemplate.get(0).get("template"));
    }
}
