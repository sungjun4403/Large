package com.project.large.gitAPI.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Annotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "annotationId")
    private Long id;

    private String gitID;
    private String annotation;
    private String filepath;
    private String annotationStartsAt;
    private String annotationEndsAt;

    @Builder
    public Annotation (String gitID, String annotation, String filepath, String annotationStartsAt, String annotationEndsAt) {
        this.gitID = gitID;
        this.annotation = annotation;
        this.filepath = filepath;
        this.annotationStartsAt = annotationStartsAt;
        this.annotationEndsAt = annotationEndsAt;
    }
}
