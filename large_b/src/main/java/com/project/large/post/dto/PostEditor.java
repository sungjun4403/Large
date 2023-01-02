package com.project.large.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostEditor {
    private String title;
    private String body;
    private Boolean ifAds;

    private String gitID;
    private String profileImg;
    private String bio;

    @Builder
    public PostEditor(String title, String body, Boolean ifAds, String gitID, String profileImg, String bio) {
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;

        this.gitID = gitID;
        this.profileImg = profileImg;
        this.bio = bio;
    }
}
