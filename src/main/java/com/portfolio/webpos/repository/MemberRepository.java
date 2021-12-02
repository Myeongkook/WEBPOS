package com.portfolio.webpos.repository;

import com.portfolio.webpos.domain.Member;

import java.util.List;

public interface MemberRepository {
    Member save(Member member);
    Member findByEmail(String email);
    List<Member> findAll();
}
