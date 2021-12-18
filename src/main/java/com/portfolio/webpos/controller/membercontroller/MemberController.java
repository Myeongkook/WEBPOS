package com.portfolio.webpos.controller.membercontroller;

import com.portfolio.webpos.domain.Member;
import com.portfolio.webpos.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @PostMapping(value = "/findpw")
    public String findPassword(String email){
        memberService.sendResetPw(email);
        return "redirect:/";
    }

    @GetMapping(value = "/resetpw")
    public String resetPassword(String key, HttpServletRequest request, Model model){
        model.addAttribute("resetKey",key);
        return "resetpw";
    }

    @PostMapping(value = "/resetpw")
    public String resetPw(String password,HttpServletRequest request,String resetKey){
        try {
            memberService.changePw(password, resetKey);
        }catch (NullPointerException e){
            return "resetpw";
        }
        return "redirect:/";
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
