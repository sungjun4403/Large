package com.project.large.post.dto;

import com.project.large.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {

    private final Long id;
    private final String title;
    private final String body;
    private final Boolean ifAds;
    private final Boolean ifComments;

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
    }

    @Builder
    public PostResponse(Long id, String title, String body, Boolean ifAds, Boolean ifComments, String gitID, String profileImg, String bio) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;
        this.ifComments = ifComments;
        this.gitID = gitID;
        this.profileImg = profileImg;
        this.bio = bio;
    }
}
