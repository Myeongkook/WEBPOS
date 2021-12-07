package com.portfolio.webpos.service;


import com.portfolio.webpos.domain.Mail;
import com.portfolio.webpos.domain.Member;
import com.portfolio.webpos.repository.MailRepository;
import com.portfolio.webpos.repository.MemberRepository;
import com.portfolio.webpos.util.MailUtil;
import com.portfolio.webpos.util.PasswdUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailRepository mailRepository;
    private final MailUtil mailUtil;
    private final PasswdUtil passwdUtil;

    @Override
    public Member signupMember(Member member) {
        member.setSalt(passwdUtil.makeSalt());
        try {
            member.setPassword(passwdUtil.makePassword(member.getPassword(), member.getSalt()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        int authCode = new Random().nextInt(999999);
        mailUtil.SendMail(member.getEmail(), authCode);
        memberRepository.save(member);
        mailRepository.save(new Mail(member, authCode));
        return member;
    }
}
