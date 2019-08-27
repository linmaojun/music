package com.music.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author linmaojun
 * @date 2019/2/20 16:13
 */
@Controller
public class IndexController {

    @GetMapping("/")
    public String login(){
        return "index";
    }
}
