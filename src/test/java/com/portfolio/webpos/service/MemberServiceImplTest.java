package com.portfolio.webpos.service;

import com.portfolio.webpos.domain.Member;
import com.portfolio.webpos.repository.MemberRepository;
import com.portfolio.webpos.util.PasswdUtil;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceImplTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PasswdUtil passwdUtil;

    @Test
    @Rollback(value = false)
    public void save() throws NoSuchAlgorithmException {
        Member savedMember = memberService.signupMember(new Member("test1234", "1234"));
        Assertions.assertThat(savedMember.getEmail()).isEqualTo("test1234");
    }

    @Test
    @Rollback(value = false)
    public void login() throws NoSuchAlgorithmException{
        Member findByIdMember = memberRepository.findById(7L);
        String result = passwdUtil.makePassword("1234", findByIdMember.getSalt());
        Assertions.assertThat(findByIdMember.getPassword()).isEqualTo(result);

        String result2 = passwdUtil.makePassword("12345", findByIdMember.getSalt());
        Assertions.assertThat(findByIdMember.getPassword()).isNotEqualTo(result2);
    }
}