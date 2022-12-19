package com.project.large.member.controller;

import com.project.large.jwt.JwtService;
import com.project.large.member.dto.IfTokenIsAuthentic;
import com.project.large.member.dto.MemberResponse;
import com.project.large.member.repository.MemberRepository;
import com.project.large.member.service.MemberService;
import com.project.large.member.dto.MemberEdit;
import com.project.large.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberService memberService;
    private final JwtService jwtService;
    private final MemberRepository memberRepository;

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

    @PatchMapping("/{gitID}/memberEdit")
    public void memberEdit(@PathVariable String gitID, @RequestBody MemberEdit memberEdit) throws IOException {
        memberService.edit(gitID, memberEdit);
    }

    @PostMapping("/ifTokenIsAuthentic")
    public Boolean ifTokenIsAuthentic (@RequestBody IfTokenIsAuthentic ifTokenIsAuthentic) {
        String extractedGitID = jwtService.extractGitID(ifTokenIsAuthentic.getAccessToken()).orElseThrow();

        if (ifTokenIsAuthentic.getGitID().equals(extractedGitID)) {
            return true;
        }
        else {
            return false;
        }
    }

    @PostMapping("/{gitID}/getUserInfo")
    public MemberResponse getMemberInfo (@RequestBody IfTokenIsAuthentic ifTokenIsAuthentic) {
        String extractedGitID = jwtService.extractGitID(ifTokenIsAuthentic.getAccessToken()).orElseThrow();
        if (ifTokenIsAuthentic.getGitID().equals(extractedGitID)) {
            MemberResponse memberResponse = memberService.createMemberResponseByMember(memberRepository.findByGitID(extractedGitID).orElseThrow());
            return memberResponse;
        }
        else {
            return null;
        }
    }

}