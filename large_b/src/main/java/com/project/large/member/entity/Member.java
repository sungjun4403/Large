package com.project.large.member.entity;

import com.project.large.member.dto.MemberEditor;
import com.project.large.post.dto.PostEditor;
import lombok.*;
import org.hibernate.type.StringNVarcharType;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member {
//element name starts with 'git' is uneditable in this service. to edit those elements, edit in github

    @Column(length = 1000)
    private String refreshToken;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberId")
    private Long id;

    private String memberToken;

    @Column(unique = true)
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
    public Member(String gitID, String memberToken, String profileImg, String name, String company, String gitBlog, String location, String gitEmail, String bio, String updated_at) {
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
    }

    public MemberEditor.MemberEditorBuilder toEditor() {
        return MemberEditor.builder()
                .memberToken(memberToken)
                .gitID(gitID)
                .profileImg(profileImg)

                .name(name)
                .company(company)
                .gitBlog(gitBlog)

                .location(location)
                .gitEmail(gitEmail)
                .bio(bio)

                .updated_at(updated_at);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void edit(MemberEditor memberEditor) {
        gitID = memberEditor.getGitID();
        memberToken = memberEditor.getMemberToken();
        profileImg = memberEditor.getProfileImg();

        name = memberEditor.getName();
        company = memberEditor.getCompany();
        location = memberEditor.getLocation();

        bio = memberEditor.getBio();
        gitBlog = memberEditor.getGitBlog();
        gitEmail = memberEditor.getGitEmail();

        updated_at = memberEditor.getUpdated_at();
    }
}
