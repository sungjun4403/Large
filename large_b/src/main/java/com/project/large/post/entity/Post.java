package com.project.large.post.entity;

import com.project.large.comment.entity.Comment;
import com.project.large.global.entity.BaseTimeEntity;
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
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String body;

    private Boolean ifAds;
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
                .gitID(gitID)
                .profileImg(profileImg)
                .bio(bio);
    }

    public void edit(PostEditor postEditor) {
        title = postEditor.getTitle();
        body = postEditor.getBody();
        ifAds = postEditor.getIfAds();

        gitID = postEditor.getGitID();
        profileImg = postEditor.getProfileImg();
        bio = postEditor.getBio();
    }
}
