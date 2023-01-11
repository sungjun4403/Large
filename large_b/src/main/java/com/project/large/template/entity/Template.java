package com.project.large.template.entity;


import com.project.large.global.entity.BaseTimeEntity;
import com.project.large.template.dto.TemplateEditor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Template extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "templateId")
    private Long id;

    private String gitID;

    private String templateName;
    private Integer templateNumber;
    private String template;
    private String hotKey;

    @Builder
    public Template(String gitID, String templateName, Integer templateNumber, String template, String hotKey) {
        this.gitID = gitID;
        this.templateName = templateName;
        this.templateNumber = templateNumber;
        this.template = template;
        this.hotKey = hotKey;
    }

    public TemplateEditor.TemplateEditorBuilder toEditor() {
        return TemplateEditor.builder()
                .gitID(gitID)
                .templateName(templateName)
                .templateNumber(templateNumber)
                .template(template)
                .hotKey(hotKey);
    }

    public void edit(TemplateEditor templateEditor) {
        gitID = templateEditor.getGitID();
        templateName = templateEditor.getTemplateName();
        templateNumber = templateEditor.getTemplateNumber();
        template = templateEditor.getTemplate();
        hotKey = templateEditor.getHotKey();
    }



//    public TemplateEditor.TemplateEditorBuiler


}
