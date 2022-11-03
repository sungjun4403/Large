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

    @Builder
    public PostCreate(String title, String body, Boolean ifAds) {
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;
    }
}
