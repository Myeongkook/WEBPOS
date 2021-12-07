package com.portfolio.webpos.repository;

import com.portfolio.webpos.domain.Mail;
import com.portfolio.webpos.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MailRepositoryImplTest {

    @Autowired
    MailRepository mailRepository;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    @Transactional
    public void saveMail(){
        Member member = new Member("myeongkook", "1234");
        memberRepository.save(member);
        mailRepository.save(new Mail(member, 1234));

    }
}