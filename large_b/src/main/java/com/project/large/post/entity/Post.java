package com.project.large.post.entity;

import com.project.large.comment.entity.Comment;
import com.project.large.global.entity.BaseTimeEntity;
import com.project.large.member.entity.Member;
import com.project.large.post.dto.PostEditor;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Builder
@AllArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    private Boolean ifAds;
    private Boolean ifComments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String gitID;
    private String profileImg;
    private String bio;

    @OneToMany(mappedBy = "post")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();


    public PostEditor.PostEditorBuilder toEditor() {
        return PostEditor.builder()
                .title(title)
                .body(body)
                .ifAds(ifAds)
                .ifComments(ifComments)
                .gitID(gitID)
                .profileImg(profileImg)
                .bio(bio);
    }

    public void edit(PostEditor postEditor) {
        title = postEditor.getTitle();
        body = postEditor.getBody();
        ifAds = postEditor.getIfAds();
        ifComments = postEditor.getIfComments();

        gitID = postEditor.getGitID();
        profileImg = postEditor.getProfileImg();
        bio = postEditor.getBio();
    }
}
