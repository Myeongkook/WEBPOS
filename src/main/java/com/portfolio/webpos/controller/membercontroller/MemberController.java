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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
@RequestMapping(value = "/member/*")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping(value = "/login")
    public String signin(HttpServletRequest request){
        HttpSession session = request.getSession();
        Member sessionCheckMember = (Member) session.getAttribute("member");
        if(!(sessionCheckMember == null)){
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping(value = "/login")
    public String signin(Member member, HttpServletRequest request){
        String login = memberService.login(member);
        HttpSession session = request.getSession();
        session.setAttribute("member", member);
        if(login.equals("index")){
            return "redirect:/";
        }else if(login.equals("auth")){
            return "auth";
        }else{
            session.invalidate();
            return "/login";
        }
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
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

    @PostMapping(value = "/auth")
    public String authMember(HttpServletRequest request, int code){
        System.out.println("code = " + code);
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        if(memberService.mailAuthentication(code, member.getEmail())){
            return "redirect:/";
        }
        return "auth";
    }
}
