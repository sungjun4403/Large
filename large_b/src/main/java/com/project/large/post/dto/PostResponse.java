package com.project.large.post.dto;

import com.project.large.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {
    //Post
    private final Long id;
    private final String title;
    private final String body;
    private final Boolean ifAds;
    private final Boolean ifComments;
    private final LocalDateTime createDate;
    private final LocalDateTime lastModifiedDate;

    //Member
    private final String gitID;
    private final String profileImg;
    private final String bio;


    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.ifAds = post.getIfAds();
        this.ifComments = post.getIfComments();

        this.gitID = post.getGitID();
        this.profileImg = post.getProfileImg();
        this.bio = post.getBio();
        this.createDate = post.getCreateDate();
        this.lastModifiedDate = post.getLastModifiedDate();
    }

    @Builder
    public PostResponse(Long id, String title, String body, Boolean ifAds, Boolean ifComments, LocalDateTime createDate, LocalDateTime lastModifiedDate, String gitID, String profileImg, String bio) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;
        this.ifComments = ifComments;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.gitID = gitID;
        this.profileImg = profileImg;
        this.bio = bio;
    }
}
