package com.project.large.post.dto;

import com.project.large.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponse {

    private final long id;
    private final String title;
    private final String body;
    private final Boolean ifAds;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.ifAds = post.getIfAds();
    }

    @Builder
    public PostResponse(Long id, String title, String body, Boolean ifAds) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;
    }
}
