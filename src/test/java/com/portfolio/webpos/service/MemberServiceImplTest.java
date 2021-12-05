package com.portfolio.webpos.service;

import com.portfolio.webpos.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Test
    @Rollback(value = false)
    public void save() {
        Member savedMember = memberService.signupMember(new Member("test1234", "12345"));
        Assertions.assertThat(savedMember.getEmail()).isEqualTo("test1234");

    }
}