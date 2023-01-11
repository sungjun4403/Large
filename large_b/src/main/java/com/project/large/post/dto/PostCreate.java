package com.project.large.post.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class PostCreate {
    private String title;
    private String body;
    private Boolean ifAds;
    private Boolean ifComments;

    private String gitID;
    private String profileImg;
    private String bio;

    @Builder
    public PostCreate(String title, String body, Boolean ifAds, Boolean ifComments, String gitID, String profileImg, String bio) {
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;
        this.ifComments = ifComments;

        this.gitID = gitID;
        this.profileImg = profileImg;
        this.bio = bio;
    }
}
