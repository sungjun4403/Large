package Controller;

import DTO.PostCreate;
import Service.PostService;
import lombok.RequiredArgsConstructor;
//import org.springframework.validation.annotation.Validated;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public void post (@RequestBody PostCreate postCreate) {
        postService.Write(postCreate);
    }

}
