package com.portfolio.webpos.service;

import com.portfolio.webpos.domain.Member;

public interface MemberService {
    Member signupMember(Member member);
    String login(Member member);
    boolean mailAuthentication(int code, String email);
    void sendResetPw(String email);
    void changePw(String password, String code);
}
