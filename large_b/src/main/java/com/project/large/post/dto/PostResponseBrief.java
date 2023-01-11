package com.project.large.post.dto;

import com.project.large.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseBrief {
    private final Long id;
    private final String title;
    private final String body;
    private final String gitID;
    private final LocalDateTime createDate;
    private final LocalDateTime lastModifiedDate;

    public PostResponseBrief(Post post, LocalDateTime time, LocalDateTime createDate, LocalDateTime lastModifiedDate) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.gitID = post.getGitID();
        this.createDate = post.getCreateDate();
        this.lastModifiedDate = post.getLastModifiedDate();
    }

    @Builder
    public PostResponseBrief(Long id, String title, String body, String gitID, LocalDateTime time, LocalDateTime createDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.gitID = gitID;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
