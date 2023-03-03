package com.project.large.post.controller;

import com.project.large.post.dto.*;
import com.project.large.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Transactional
public class PostController {
    private final PostService postService;

    //CREATE
    @PostMapping("/post")
    public void create (@RequestBody PostCreateRequest postCreateRequest, List<MultipartFile> multipartFiles) {
        PostCreate postCreate = postService.createPostCreate(postCreateRequest);
        postService.write(postCreate, multipartFiles);
    }

    //VIEW ALL
    @GetMapping("/post")
    public List<PostResponse> getList() {
        return postService.getList();
    }

    //VIEW ALL BY GITID - BRIEF (WITHOUT PROFILEIMG)
    @GetMapping("/post/brief/{gitID}")
    public List<PostResponseBrief> getAllPostByGitID(@PathVariable String gitID) {
        return postService.getAllPostBrieflyByGitID(gitID);
    }

    //VIEW ONE
    @GetMapping("/post/{postId}")
    public PostResponse get(@PathVariable long postId) {
        return postService.get(postId);
    }

    //EDIT
    @PatchMapping("/post/{postId}")
    public void edit(@PathVariable long postId, @RequestBody PostEdit postEdit) {
        postService.edit(postEdit, postId);
    }

    //DELETE
    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
