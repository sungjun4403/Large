package com.project.large.post.service;

import com.project.large.global.utils.SecurityUtil;
import com.project.large.post.dto.PostCreate;
import com.project.large.post.dto.PostEdit;
import com.project.large.post.dto.PostEditor;
import com.project.large.post.dto.PostResponse;
import com.project.large.post.entity.Post;
import com.project.large.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .gitID(post.getGitID())
                .profileImg(post.getProfileImg())
                .bio(post.getBio())
                .build();
    }

    //EDIT
    @Transactional
    public void edit (Long id, PostEdit postEdit) {

        Post post = postRepository.findById(id).orElseThrow();

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        if(postEdit.getTitle() != null) { //?????? ?????? ?????? ????????? ??????
            editorBuilder.title(postEdit.getTitle());
        }
        if(postEdit.getBody() != null) {
            editorBuilder.body(postEdit.getBody());
        }
        if(postEdit.getIfAds() != null) {
            editorBuilder.ifAds(postEdit.getIfAds());
        }
        post.edit(editorBuilder.build());
    }

    //DELETE
    public void delete (Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }
}
