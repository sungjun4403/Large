package Service;

import DTO.PostCreate;
import Entity.Post;
import Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public void Write (PostCreate postCreate) {

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .body(postCreate.getBody())
                .ifads(postCreate.getIfads())
                .build();

        postRepository.save(post);

    }
}
