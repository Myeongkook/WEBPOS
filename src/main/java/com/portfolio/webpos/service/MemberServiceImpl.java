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
import java.util.List;
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
        member.setPassword(passwdUtil.makePassword(member.getPassword(), member.getSalt()));
        int authCode = new Random().nextInt(999999);
        mailUtil.sendCodeMail(member.getEmail(), authCode);
        memberRepository.save(member);
        mailRepository.save(new Mail(member, authCode));
        return member;
    }

    @Override
    public String login(Member member) {
        Member byEmail = memberRepository.findByEmail(member.getEmail());
        if(byEmail == null){
            return "login";
        }
        PasswdUtil passwdUtil = new PasswdUtil();
        String s = passwdUtil.makePassword(member.getPassword(), byEmail.getSalt());
        if(!s.equals(byEmail.getPassword())){
            return "login";
        }
        if(memberRepository.findAuthStatus(member.getEmail())){
            return "index";
        }else{
            return "auth";
        }
    }

    @Override
    public boolean mailAuthentication(int code, String email) {
        Member findByEmailMember = memberRepository.findByEmail(email);
        Mail findByMemberIdMail = mailRepository.findByMemberId(findByEmailMember.getId());
        if(findByMemberIdMail.getCode()==code){
            memberRepository.modifyMemberAuth(findByEmailMember.getId());
            return true;
        }
        return false;
    }

    @Override
    public void sendResetPw(String email) {
        Member findByEmailMember = memberRepository.findByEmail(email);
        String resetMailHash = passwdUtil.makePassword(email, findByEmailMember.getSalt());
        mailUtil.sendResetPwMail(email, resetMailHash);
    }

    @Override
    public void changePw(String password, String code) {
        List<Member> allMember = memberRepository.findAll();
        for (Member member : allMember) {
            String savedMailPassword = passwdUtil.makePassword(member.getEmail(), member.getSalt());
            if(savedMailPassword.equals(code)){
                Member byEmail = memberRepository.findByEmail(member.getEmail());
                byEmail.setPassword(passwdUtil.makePassword(password, byEmail.getSalt()));
            }

        }
    }


}
