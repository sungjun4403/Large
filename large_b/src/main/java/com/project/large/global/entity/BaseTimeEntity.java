package com.project.large.global.entity;

import com.project.large.global.utils.DateTimeUtils;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate = DateTimeUtils.now();

    @LastModifiedDate
    @Column(updatable = true)
    private LocalDateTime lastModifiedDate = DateTimeUtils.now();
}
