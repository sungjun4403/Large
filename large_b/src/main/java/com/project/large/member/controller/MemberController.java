package com.project.large.member.controller;

import com.project.large.global.utils.SecurityUtil;
import com.project.large.jwt.JwtService;
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
        return ResponseEntity.ok().headers(headers).body(null);
    }

    @PatchMapping("/memberEdit/{gitID}")
    public void memberEdit(@PathVariable String gitID, @RequestBody MemberEdit memberEdit) throws IOException {
        String extractedGitID = SecurityUtil.getLoginedUserGitId();

        if (gitID.equals(extractedGitID)) {
            memberService.edit(gitID, memberEdit);
        }
        else {
        }

    }

    @GetMapping("/ifTokenIsAuthentic/{gitID}")
    public Boolean ifTokenIsAuthentic (@PathVariable String gitID) {
        String extractedGitID = SecurityUtil.getLoginedUserGitId();

        if (gitID.equals(extractedGitID)) {
            return true;
        }
        else {
            return false;
        }
    }

    @GetMapping("/getUserInfo/{gitID}")
    public MemberResponse getMemberInfo (@PathVariable String gitID) {
        String extractedGitID = SecurityUtil.getLoginedUserGitId();
        if (gitID.equals(extractedGitID)) {
            MemberResponse memberResponse = memberService.
                    createMemberResponseByMember(memberRepository.findByGitID(extractedGitID).orElseThrow());
            return memberResponse;
        }
        else {
            return null;
        }
    }

    @GetMapping("/deleteNCascade/{gitID}")
    public void deleteNCascade (@PathVariable String gitID) {
        String extractedGitID = SecurityUtil.getLoginedUserGitId();
        if (gitID.equals(extractedGitID)) {
            memberService.deleteNCascade(extractedGitID);
        }
        else {
            return;
        }
    }
}