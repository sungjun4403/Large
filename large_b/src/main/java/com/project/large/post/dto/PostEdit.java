package com.project.large.post.dto;

import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class PostEdit {
    private String title;
    private String body;
    private Boolean ifAds;


    public PostEdit(String title, String body, Boolean ifAds) {
        this.title = title;
        this.body = body;
        this.ifAds = ifAds;
    }
}