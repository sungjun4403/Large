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
    private String profileImg;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private String bio;
    private String updated_at;

    @Builder
    public MemberCreate(String gitID, String profileImg, String name, String company, String blog, String location, String email, String bio, String updated_at) {
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
