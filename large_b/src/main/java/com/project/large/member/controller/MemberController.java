package com.project.large.member.controller;

import com.project.large.member.dto.MemberEdit;
import com.project.large.member.dto.OAuthToken;
import com.project.large.member.entity.Member;
import com.project.large.member.service.MemberService;
import com.project.large.post.dto.PostEdit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/auth/github/callback")
    public void GitLogin(@RequestParam String code, RedirectAttributes redirectAttributes) throws IOException {
        Map memberInfo = memberService.getGitToken(code, redirectAttributes);
        String jwtToken = memberService.signInAndGetToken(memberInfo);
        Member member = memberService.getMemberByAccessToken(jwtToken);

        HttpHeaders headers = new HttpHeaders();
        headers.add("AccessToken", jwtToken);
        headers.add("RefreshToken", member.getRefreshToken());

    }

    @PatchMapping("/user/{gitID}")
    public void memberEdit(@PathVariable String gitID, @RequestBody MemberEdit memberEdit) throws IOException {
        memberService.edit(gitID, memberEdit);
    }

}