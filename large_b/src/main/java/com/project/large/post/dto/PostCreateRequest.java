package com.project.large.post.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostCreateRequest {
    private String title;
    private String body;
    private Boolean ifAds;
    private Boolean ifComments;

    @Builder
    public PostCreateRequest(String title, String body, Boolean ifAds, Boolean ifComments) {
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;
        this.ifComments = ifComments;
    }
}
