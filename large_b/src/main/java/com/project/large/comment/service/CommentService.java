package com.project.large.comment.service;

import com.project.large.comment.entity.Comment;
import com.project.large.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void delete(Long id) {
        Comment toDeleteComment = commentRepository.findById(id).orElseThrow();
        commentRepository.delete(toDeleteComment);
    }
}
