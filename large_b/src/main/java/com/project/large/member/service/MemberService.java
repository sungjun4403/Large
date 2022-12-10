package com.project.large.member.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.large.jwt.JwtService;
import com.project.large.member.dto.MemberCreate;
import com.project.large.member.dto.MemberEdit;
import com.project.large.member.dto.MemberEditor;
import com.project.large.member.entity.Member;
import com.project.large.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;

    public Map getGitToken (String code, RedirectAttributes redirectAttributes) throws IOException{
        URL url = new URL("https://github.com/login/oauth/access_token");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");

        // 이 부분에 client_id, client_secret, code를 넣어주자.
        // 여기서 사용한 secret 값은 사용 후 바로 삭제하였다.
        // 실제 서비스나 깃허브에 올릴 때 이 부분은 항상 주의하자.
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
            bw.write("client_id=010fc7e8d7cd79b2d8cb&client_secret=87978e9da324b52e8e45550784972fffbfaa0823&code=" + code);
            bw.flush();
        }

        int responseCode = conn.getResponseCode();

        String responseData = getResponse(conn, responseCode);

        conn.disconnect();

        return access(responseData, redirectAttributes);
    }

    public Map access(String response, RedirectAttributes redirectAttributes) throws IOException {

        /**
         * JSON 데이터를 처리하기 위해
         * Spring Boot에 기본적으로 포함되어 있는 Jackson 라이브러리를 사용함
         **/
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(response, Map.class);
        String access_token = map.get("access_token");

        URL url = new URL("https://api.github.com/user");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");
        conn.setRequestProperty("Authorization", "token " + access_token);

        int responseCode = conn.getResponseCode();
        Map<String,Object> result = new ObjectMapper().readValue(getResponse(conn, responseCode), HashMap.class);
        conn.disconnect();

        return result;
    }

    private String getResponse(HttpURLConnection conn, int responseCode) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (responseCode == 200) {
            try (InputStream is = conn.getInputStream();
                 BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                for (String line = br.readLine(); line != null; line = br.readLine()) {
                    sb.append(line);
                }
            }
        }
        return sb.toString();
    }

    public String signInAndGetToken(Map memberInfo) {

        if (memberRepository.findByGitID((String) memberInfo.get("login")).isEmpty()) { //계정 없으면
            MemberCreate memberCreate = MemberCreate.builder()
                    .gitID((String) memberInfo.get("login"))
                    .memberToken((String) createMemberToken())
                    .profileImg((String) memberInfo.get("avatar_url"))
                    .name((String) memberInfo.get("name"))
                    .company((String) memberInfo.get("company"))
                    .gitBlog((String) memberInfo.get("blog"))
                    .location((String) memberInfo.get("location"))
                    .gitEmail((String) memberInfo.get("email"))
                    .bio((String) memberInfo.get("bio"))
                    .updated_at((String) memberInfo.get("updated_at"))
                    .build();

            Member member = Member.builder()
                    .gitID(memberCreate.getGitID())
                    .memberToken(memberCreate.getMemberToken())
                    .profileImg(memberCreate.getProfileImg())
                    .name(memberCreate.getName())
                    .company(memberCreate.getCompany())
                    .gitBlog(memberCreate.getGitBlog())
                    .location(memberCreate.getLocation())
                    .gitEmail(memberCreate.getGitEmail())
                    .bio(memberCreate.getBio())
                    .updated_at(memberCreate.getUpdated_at())
                    .build();

            memberRepository.save(member);
            member.updateRefreshToken(jwtService.createRefreshToken());
            return jwtService.createAccessToken(member);
        }
        else { //계정 있으면 로그인
            Member member = memberRepository.findByGitID((String) memberInfo.get("login")).orElseThrow();
            member.updateRefreshToken(jwtService.createRefreshToken());
            return jwtService.createAccessToken(member);
        }
    }


    public String createMemberToken() {
        Random random = new Random();
        int length = random.nextInt(5) + 5;

        StringBuffer newWord= new StringBuffer();
        for (int i = 0; i < length; i++ ) {
            int choice = random.nextInt(3);
            switch (choice) {
                case 0:
                    newWord.append((char)(int) random.nextInt(25)+97);
                    break;
                case 1:
                    newWord.append((char)(int) random.nextInt(25)+65);
                    break;
                case 2:
                    newWord.append((char)(int) random.nextInt(10)+48);
                    break;
            }
        }
        return newWord.toString();
    }

    public Member getMemberByAccessToken(String jwtToken) {
        String gitId = jwtService.extractGitID(jwtToken).orElseThrow();
        Member member  = memberRepository.findByGitID(gitId).orElseThrow();
        return member;
    }

    @Transactional
    public void edit(String gitId, MemberEdit memberEdit) { //Editable 5 elements : profileImg, name, company, location, bio
        Member member = memberRepository.findByGitID(gitId).orElseThrow();
        MemberEditor.MemberEditorBuilder memberEditorBuilder = member.toEditor();

        if (memberEdit.getProfileImg() != null) {
            memberEditorBuilder.profileImg(memberEdit.getProfileImg());
        }

        if (memberEdit.getName() != null) {
            memberEditorBuilder.name(memberEdit.getName());
        }

        if (memberEdit.getCompany() != null) {
            memberEditorBuilder.company(memberEdit.getCompany());
        }

        if (memberEdit.getLocation() != null) {
            memberEditorBuilder.location(memberEdit.getLocation());
        }

        if (memberEdit.getBio() != null) {
            memberEditorBuilder.bio(memberEdit.getBio());
        }

        memberEditorBuilder.gitID(member.getGitID());
        memberEditorBuilder.memberToken(member.getMemberToken());
        memberEditorBuilder.gitBlog(member.getGitBlog());
        memberEditorBuilder.gitEmail(member.getGitEmail());
        memberEditorBuilder.updated_at(member.getUpdated_at());

        member.edit(memberEditorBuilder.build());

    }

    public void getMemberByBodyAccessToken(String BodyAccessToken) throws JsonProcessingException {
        Map<String,Object> result = new ObjectMapper().readValue(BodyAccessToken, HashMap.class);

        String AccessToken = (String) result.get("AccessToken");

        String gitID = String.valueOf(jwtService.extractGitID(AccessToken));
        System.out.println(gitID);

    }
}
