package com.project.large.post.repository;

import com.project.large.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT * FROM post", nativeQuery = true)
    List<Post> getList();
    List<Post> findByGitID(String gitID);
}
