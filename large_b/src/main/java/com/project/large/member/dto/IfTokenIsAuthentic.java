package com.project.large.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class IfTokenIsAuthentic {
    private String AccessToken;
    private String gitID;

    @Builder
    public IfTokenIsAuthentic(String AccessToken, String gitID) {
        this.AccessToken = AccessToken;
        this.gitID = gitID;
    }
}
