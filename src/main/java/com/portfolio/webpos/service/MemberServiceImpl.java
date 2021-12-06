package com.portfolio.webpos.service;


import com.portfolio.webpos.domain.Member;
import com.portfolio.webpos.repository.MemberRepository;
import com.portfolio.webpos.util.PasswdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswdUtil passwdUtil;

    @Override
    public Member signupMember(Member member) throws NoSuchAlgorithmException {
        member.setSalt(passwdUtil.makeSalt());
        member.setPassword(passwdUtil.makePassword(member.getPassword(), member.getSalt()));
        return memberRepository.save(member);
    }
}
