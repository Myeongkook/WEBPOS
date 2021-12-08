package com.portfolio.webpos.service;

import com.portfolio.webpos.domain.Member;

import java.security.NoSuchAlgorithmException;

public interface MemberService {
    Member signupMember(Member member);
    String login(Member member);
    boolean mailAuthentication(int code, String email);
}
