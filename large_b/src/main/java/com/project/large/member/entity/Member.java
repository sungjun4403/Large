package com.project.large.member.entity;

import lombok.*;
import org.hibernate.type.StringNVarcharType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gitID;
    private String profileImg;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String bio;
    private String updated_at;

    @Builder
    public Member(String gitID, String profileImg, String name, String company, String blog, String location, String email, String bio, String updated_at) {
        this.gitID = gitID;
        this.profileImg = profileImg;
        this.name = name;
        this.company = company;
        this.blog = blog;
        this.location = location;
        this.email = email;
        this.bio = bio;
        this.updated_at = updated_at;
    }

}
