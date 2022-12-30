package com.project.large.comment.entity;

import com.project.large.global.entity.BaseTimeEntity;
import com.project.large.post.entity.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Comment extends BaseTimeEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Id
    @GeneratedValue
    private Long id;
    private String gitID;
    private String userName;
    private String profileImg;
    private Integer upVotes;
    private String content;
    @ElementCollection
    private Map<String, Integer> emoji = new HashMap<String, Integer>();

    @Builder
    public Comment (String gitID, String userName, String profileImg, Integer upVotes, String content, Map emoji) {
        this.gitID = gitID;
        this.userName = userName;
        this.profileImg = profileImg;
        this.upVotes = upVotes;
        this.content = content;
        this.emoji = emoji;
    }
}
