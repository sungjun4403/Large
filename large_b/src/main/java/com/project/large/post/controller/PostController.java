package com.project.large.post.controller;

import com.project.large.post.dto.PostCreate;
import com.project.large.post.dto.PostEdit;
import com.project.large.post.dto.PostResponse;
import com.project.large.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    //CREATE
    @PostMapping("/post")
    public void create (@RequestBody PostCreate postCreate) {
        postService.write(postCreate);
    }

    //VIEW ALL
    @GetMapping("/post")
    public List<PostResponse> getList() {
        return postService.getList();
    }

    //VIEW ONE
    @GetMapping("/post/{postId}")
    public PostResponse get(@PathVariable long postId) {
        return postService.get(postId);
    }

    //EDIT
    @PatchMapping("/post/{postId}")
    public void edit(@PathVariable long postId, @RequestBody PostEdit postEdit) {
        postService.edit(postId, postEdit);
    }

    //DELETE
    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
