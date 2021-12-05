package com.portfolio.webpos.service;


import com.portfolio.webpos.domain.Member;
import com.portfolio.webpos.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member signupMember(Member member) {
        return memberRepository.save(member);
    }
}
