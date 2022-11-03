package com.project.large.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostEditor {
    private final String title;
    private final String body;
    private final Boolean ifAds;

    @Builder
    public PostEditor(String title, String body, Boolean ifAds) {
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;
    }
}
