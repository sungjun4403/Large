package com.project.large.post.dto;

import com.project.large.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostResponseBrief {
    private final Long id;
    private final String title;
    private final String body;
    private final String gitID;

    public PostResponseBrief (Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.gitID = post.getGitID();
    }

    @Builder
    public PostResponseBrief (Long id, String title, String body, String gitID) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.gitID = gitID;
    }
}
