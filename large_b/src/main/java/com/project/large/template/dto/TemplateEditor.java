package com.project.large.template.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class TemplateEditor {
    private String gitID;

    private String templateName;
    private Integer templateNumber;
    private String template;
    private String hotKey;

    @Builder
    public TemplateEditor (String gitID, String templateName, Integer templateNumber, String template, String hotKey) {
        this.gitID = gitID;
        this.templateName = templateName;
        this.templateNumber = templateNumber;
        this.template = template;
        this.hotKey = hotKey;
    }
}
