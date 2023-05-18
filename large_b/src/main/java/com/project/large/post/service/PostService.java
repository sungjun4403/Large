package com.project.large.post.service;

import com.project.large.global.service.S3Uploader;
import com.project.large.global.utils.SecurityUtil;
import com.project.large.member.entity.Member;
import com.project.large.member.repository.MemberRepository;
import com.project.large.post.dto.*;
import com.project.large.post.entity.Post;
import com.project.large.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;

    //CREATE
    public void write (PostCreate postCreate, List<MultipartFile> multipartFiles) {
        List <String> thumbnails = new ArrayList<>();

        Post post = Post.builder()
                .title(postCreate.getTitle())
                .body(postCreate.getBody())
                .ifAds(postCreate.getIfAds())

                .build();

        if (multipartFiles.size() != 0) {
            List<String> fileNames = s3Uploader.uploadImages(multipartFiles);
            fileNames.forEach(file -> {
                thumbnails.add(s3Uploader.getThumbnailPath(file));
            });
            post.addImages(thumbnails);
        }

        postRepository.save(post);
    }

    //VIEW ALL
    public List<PostResponse> getList() {
        return postRepository.getList().stream()
                .map(post -> new PostResponse(post))
                .collect(Collectors.toList());
    }

    //VIEW ONE
    public PostResponse get(Long id){
        Post post = postRepository.findById(id).orElseThrow();

        return PostResponse.builder()
                .id(post.getId())

                .title(post.getTitle())
                .body(post.getBody())
                .ifAds(post.getIfAds())
                .ifComments(post.getIfComments())
                .gitID(post.getGitID())
                .images(new ArrayList<>())
                .profileImg(post.getProfileImg())
                .bio(post.getBio())
                .createDate(post.getCreateDate())
                .lastModifiedDate(post.getLastModifiedDate())
                .build();
    }

    //EDIT
    @Transactional
    public void edit (PostEdit postEdit, Long id) {

        Post post = postRepository.findById(id).orElseThrow();

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        if(postEdit.getTitle() != null) { //수정 요청 값이 있으면 대체
            editorBuilder.title(postEdit.getTitle());
        }
        if(postEdit.getBody() != null) {
            editorBuilder.body(postEdit.getBody());
        }
        if(postEdit.getIfAds() != null) {
            editorBuilder.ifAds(postEdit.getIfAds());
        }
        if(postEdit.getIfComments() != null) {
            editorBuilder.ifComments(postEdit.getIfComments());
        }
        post.edit(editorBuilder.build());
    }

    //DELETE
    public void delete (Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }

    public List<PostResponseBrief> getAllPostBrieflyByGitID(String gitID) throws IllegalStateException {
        if (memberRepository.findByGitID(gitID).isEmpty()) {
            IllegalStateException IllegalStateException = new IllegalStateException("User not exist");
            throw IllegalStateException;
        }

        List<Post> postByGitID = postRepository.findByGitID(gitID);
        List<PostResponseBrief> postResponseBriefList = new ArrayList<>();

        postByGitID.forEach(post -> {
            PostResponseBrief toAdd = PostResponseBrief.builder()
                    .id(post.getId())
                    .gitID(post.getGitID())
                    .body(post.getBody())
                    .title(post.getTitle())
                    .createDate(post.getCreateDate())
                    .lastModifiedDate(post.getLastModifiedDate())
                    .build();
            postResponseBriefList.add(toAdd);
        });

        return postResponseBriefList;

    }

    public PostCreate createPostCreate(PostCreateRequest postCreateRequest) {
        String gitID = SecurityUtil.getLoginedUserGitId();
        Member member = memberRepository.findByGitID(gitID).orElseThrow();

        System.out.println(member);
        PostCreate postCreate = PostCreate.builder()
                .title(postCreateRequest.getTitle())
                .body(postCreateRequest.getBody())
                .ifAds(postCreateRequest.getIfAds())
                .ifComments(postCreateRequest.getIfComments())
                .images(null)
                .gitID(member.getGitID())
//                .gitID("sungjun4403")
                .profileImg(member.getProfileImg())
                .bio(member.getBio())
                .build();
        return postCreate;
    }
}







