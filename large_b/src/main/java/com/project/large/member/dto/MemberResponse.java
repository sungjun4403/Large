package com.project.large.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberResponse {
    private String gitID;
    private String profileImg;
    private String name;
    private String company;
    private String gitBlog;
    private String location;
    private String gitEmail;
    private String bio;
    private String updated_at;

    @Builder
    public MemberResponse(String gitID, String profileImg, String name, String company, String gitBlog, String location, String gitEmail, String bio, String updated_at) {
        this.gitID = gitID;
        this.profileImg = profileImg;
        this.name = name;
        this.company = company;
        this.gitBlog = gitBlog;
        this.location = location;
        this.gitEmail = gitEmail;
        this.bio = bio;
        this.updated_at = updated_at;
    }
}
