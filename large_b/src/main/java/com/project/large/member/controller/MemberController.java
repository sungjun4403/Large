package com.project.large.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.large.member.dto.MemberIfMe;
import com.project.large.member.service.MemberService;
import com.project.large.member.dto.MemberEdit;
import com.project.large.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/auth/github/callback")
    public ResponseEntity GitLogin(@RequestParam String code, RedirectAttributes redirectAttributes) throws IOException {
        Map memberInfo = memberService.getGitToken(code, redirectAttributes);
        String jwtToken = memberService.signInAndGetToken(memberInfo);
        Member member = memberService.getMemberByAccessToken(jwtToken);
        String MemberToken = member.getMemberToken();

        HttpHeaders headers = new HttpHeaders();

        headers.add("AccessToken", jwtToken);
        headers.add("MemberToken", MemberToken);
        headers.add("RefreshToken", member.getRefreshToken());
        headers.add("gitID", member.getGitID());
        System.out.println(member.getGitID());
        return ResponseEntity.ok().headers(headers).body(null);
    }

    @PatchMapping("/user/{gitID}")
    public void memberEdit(@PathVariable String gitID, @RequestBody MemberEdit memberEdit) throws IOException {
        memberService.edit(gitID, memberEdit);
    }

    @GetMapping("/user/{gitID}")
    public void GetMemberInfo(@PathVariable String gitID) {
//        memberService
    }

    @PostMapping("/getUserInfo")
    public Member GetUserInfo (@RequestBody String BodyAccessToken) throws JsonProcessingException {
        Member member = memberService.getMemberByBodyAccessToken(BodyAccessToken);
        return member;
    }


}