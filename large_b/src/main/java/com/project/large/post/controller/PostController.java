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

    @PostMapping("/post")
    public void post (@RequestBody PostCreate postCreate) {
        postService.write(postCreate);
    }

    @GetMapping("/post")
    public List<PostResponse> getList() {
        return postService.getList();
    }

    @GetMapping("/post/{postId}")
    public PostResponse get(@PathVariable long postId) {
        return postService.get(postId);
    }

    @PatchMapping("/post/{postId}")
    public void edit(@PathVariable long postId, @RequestBody PostEdit postEdit) {
        postService.edit(postId, postEdit);
    }

    @DeleteMapping("/post/{postId}")
    public void delete(@PathVariable Long postId) {
        postService.delete(postId);
    }
}
