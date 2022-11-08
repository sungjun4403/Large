package com.project.large.member.controller;

import com.project.large.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/auth/github/callback")
    public void GitLogin(@RequestParam String code, RedirectAttributes redirectAttributes) throws IOException {
        Map memberInfo = memberService.getGitToken(code, redirectAttributes);
        System.out.println(memberInfo);
        memberService.signIn(memberInfo);

    }

}