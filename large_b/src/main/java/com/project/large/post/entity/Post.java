package com.project.large.post.entity;

import com.project.large.global.entity.BaseTimeEntity;
import com.project.large.post.dto.PostEditor;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String body;

    private Boolean ifAds;

    @Builder
    public Post(String title, String body, Boolean ifAds) {
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;
    }

    public PostEditor.PostEditorBuilder toEditor() {
        return PostEditor.builder()
                .title(title)
                .body(body)
                .ifAds(ifAds);
    }

    public void edit(PostEditor postEditor) {
        title = postEditor.getTitle();
        body = postEditor.getBody();
        ifAds = postEditor.getIfAds();
    }
}
