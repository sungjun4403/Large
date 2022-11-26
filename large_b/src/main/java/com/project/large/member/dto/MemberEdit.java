package com.project.large.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//get member for memberService.edit() to compare which element to edit

@Getter
@Setter
@ToString
public class MemberEdit { // 9
    private String memberToken;
    private String gitID;
    private String profileImg;

    private String name;
    private String company;
    private String gitBlog;

    private String location;
    private String email;
    private String bio;

    private String updated_at;


    public MemberEdit(String memberToken, String gitID, String profileImg, String name, String company, String gitBlog, String location, String email, String bio, String updated_at) { // 9
        this.memberToken = memberToken;
        this.gitID = gitID;
        this.profileImg = profileImg;

        this.name = name;
        this.company = company;
        this.gitBlog = gitBlog;

        this.location = location;
        this.email = email;
        this.bio = bio;

        this.updated_at = updated_at;
    }
}
