package com.project.large.post.service;

import com.project.large.global.utils.SecurityUtil;
import com.project.large.post.dto.*;
import com.project.large.post.entity.Post;
import com.project.large.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    //CREATE
    public void write (PostCreate postCreate) {
        Post post = Post.builder()
                .title(postCreate.getTitle())
                .body(postCreate.getBody())
                .ifAds(postCreate.getIfAds())
                .ifComments(postCreate.getIfComments())
                .gitID(postCreate.getGitID())
                .profileImg(postCreate.getProfileImg())
                .bio(postCreate.getBio())
                .build();

        postRepository.save(post);
    }

    //VIEW ALL
    public List<PostResponse> getList() {
        return postRepository.getList().stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    //VIEW ONE
    public PostResponse get(Long id){
        Post post = postRepository.findById(id).orElseThrow();

        return PostResponse.builder()
                .id(post.getId())

                .title(post.getTitle())
                .body(post.getBody())
                .ifAds(post.getIfAds())
                .ifComments(post.getIfComments())
                .gitID(post.getGitID())
                .profileImg(post.getProfileImg())
                .bio(post.getBio())
                .build();
    }

    //EDIT
    @Transactional
    public void edit (PostEdit postEdit, Long id) {

        Post post = postRepository.findById(id).orElseThrow();

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        if(postEdit.getTitle() != null) { //수정 요청 값이 있으면 대체
            editorBuilder.title(postEdit.getTitle());
        }
        if(postEdit.getBody() != null) {
            editorBuilder.body(postEdit.getBody());
        }
        if(postEdit.getIfAds() != null) {
            editorBuilder.ifAds(postEdit.getIfAds());
        }
        if(postEdit.getIfComments() != null) {
            editorBuilder.ifComments(postEdit.getIfComments());
        }
        post.edit(editorBuilder.build());
    }

    //DELETE
    public void delete (Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }

    public List<PostResponseBrief> getAllPostBrieflyByGitID(String gitID) {

        List<Post> postByGitID = postRepository.findByGitID(gitID);
        List<PostResponseBrief> postResponseBriefList = new ArrayList<>();

        postByGitID.forEach(post -> {
            PostResponseBrief toAdd = PostResponseBrief.builder()
                    .id(post.getId())
                    .gitID(post.getGitID())
                    .body(post.getBody())
                    .title(post.getTitle())
                    .build();
            postResponseBriefList.add(toAdd);
        });

        return postResponseBriefList;

    }
}
