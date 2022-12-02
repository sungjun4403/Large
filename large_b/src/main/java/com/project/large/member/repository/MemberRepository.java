package com.project.large.member.repository;

import com.project.large.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByGitID(String gitID);

    Optional<Member> findByRefreshToken(String refreshToken);
}
