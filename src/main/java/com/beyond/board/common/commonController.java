package com.beyond.board.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class commonController {

    @GetMapping("/home")
    public String home(){
        return "common/home";
    }

}
