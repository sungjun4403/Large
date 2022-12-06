package com.project.large.gitAPI.dto;

import com.project.large.gitAPI.entity.Annotation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AnnotationCreate {
    private String gitID;
    private String annotation;
    private String filepath;
    private String annotationStartsAt;
    private String annotationEndsAt;

    @Builder
    public AnnotationCreate (String gitID, String annotation, String filepath, String annotationStartsAt, String annotationEndsAt) {
        this.gitID = gitID;
        this.annotation = annotation;
        this.filepath = filepath;
        this.annotationStartsAt = annotationStartsAt;
        this.annotationEndsAt = annotationEndsAt;
    }
}
