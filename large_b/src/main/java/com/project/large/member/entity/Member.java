package com.project.large.member.entity;

import com.project.large.comment.entity.Comment;
import com.project.large.global.entity.BaseTimeEntity;
import com.project.large.member.dto.MemberEditor;
import com.project.large.post.entity.Post;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Member extends BaseTimeEntity {
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
    private Integer commits;

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private List<Post> post = new ArrayList<>();

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

                .updated_at(updated_at)
                .commits(commits);
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
        commits = memberEditor.getCommits();
    }
}
