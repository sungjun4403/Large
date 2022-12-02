package com.project.large.gitAPI.repository;

import com.project.large.gitAPI.entity.Annotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AnnotationRepository extends JpaRepository<Annotation, Long> {

    List <Annotation> findByFilepath(String filepath);
}
