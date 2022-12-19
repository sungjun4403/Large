package com.project.large.gitAPI.controller;

import com.project.large.gitAPI.service.GitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class gitController {
    private final GitService gitService;

    @GetMapping("/getcode")
    public void getCode() throws IOException {
        String path = "sungjun4403/O_baksa_go/main/OBSpjct/OBSapp/views.py";
        List<String> getCode = gitService.getSourceFile(path);
    }

    @PostMapping("/")
}
