package com.project.large.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostEditor {
    private String title;
    private String body;
    private Boolean ifAds;
    private Boolean ifComments;
    private List<String> images;
    private String gitID;
    private String profileImg;
    private String bio;

    @Builder
    public PostEditor(String title, String body, Boolean ifAds, Boolean ifComments, String gitID, String profileImg, String bio, List<String> images) {
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;
        this.ifComments = ifComments;
        this.images = images;
        this.gitID = gitID;
        this.profileImg = profileImg;
        this.bio = bio;
    }
}
