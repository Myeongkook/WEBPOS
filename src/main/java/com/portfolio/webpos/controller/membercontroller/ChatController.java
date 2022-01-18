package com.portfolio.webpos.controller.membercontroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }
}
