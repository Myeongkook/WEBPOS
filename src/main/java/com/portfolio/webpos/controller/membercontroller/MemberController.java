package com.portfolio.webpos.controller.membercontroller;

import com.portfolio.webpos.domain.Mail;
import com.portfolio.webpos.domain.Member;
import com.portfolio.webpos.service.MemberService;
import com.portfolio.webpos.util.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/member/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping(value = "/login")
    public String signin(){
        return "login";
    }

    @PostMapping(value = "/login")
    public String signin(Member member){
        return "redirect:/";
    }

    @GetMapping(value = "/findpw")
    public String findPassword(){
        return "findpw";
    }

    @GetMapping(value = "/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signup(Member member){
        memberService.signupMember(member);
        return "redirect:/";
    }
}
