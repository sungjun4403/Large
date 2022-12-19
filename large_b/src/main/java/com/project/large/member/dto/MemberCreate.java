package com.project.large.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.type.StringNVarcharType;

@Setter
@Getter
@ToString
public class MemberCreate {
    private String gitID;
    private String memberToken;
    private String profileImg;
    private String name;
    private String company;
    private String gitBlog;
    private String location;
    private String gitEmail;
    private String bio;
    private String updated_at;
    private Integer commits;

    @Builder
    public MemberCreate(String gitID, String memberToken, String profileImg, String name, String company, String gitBlog, String location, String gitEmail, String bio, String updated_at, Integer commits) {
        this.gitID = gitID;
        this.memberToken = memberToken;
        this.profileImg = profileImg;
        this.name = name;
        this.company = company;
        this.gitBlog = gitBlog;
        this.location = location;
        this.gitEmail = gitEmail;
        this.bio = bio;
        this.updated_at = updated_at;
        this.commits = commits;
    }

}
