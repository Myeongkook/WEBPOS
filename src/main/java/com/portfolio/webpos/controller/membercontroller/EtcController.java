package com.portfolio.webpos.controller.membercontroller;

import com.portfolio.webpos.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EtcController {

    @RequestMapping(value = "/help", method = RequestMethod.GET)
    public String help(){
        return "help";
    }

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request){
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        if(member == null){
            return "redirect:/member/login";
        }
        return "index";
    }

    @GetMapping("/cctv")
    public String cctv(){
        return "cctv";
    }
}
