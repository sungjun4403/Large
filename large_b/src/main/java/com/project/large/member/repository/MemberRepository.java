package com.project.large.member.repository;


import com.project.large.member.entity.Member;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByGitID(String gitID);
}
