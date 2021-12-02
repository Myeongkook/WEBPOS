package com.portfolio.webpos.controller.membercontroller;

import com.portfolio.webpos.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/member/*", method = RequestMethod.GET)
public class MemberController {

    @GetMapping(value = "/login")
    public String signin(){
        return "login";
    }

    @PostMapping(value = "/signin")
    public String signin(Member member){
        return "redirect:index";
    }

    @RequestMapping(value = "/findpw", method = RequestMethod.GET)
    public String findPassword(){
        return "findpw";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(){
        return "signup";
    }
}
