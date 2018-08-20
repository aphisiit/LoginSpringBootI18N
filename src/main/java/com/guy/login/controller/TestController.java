package com.guy.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class TestController {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String index(Principal principal,Model model){
        LOGGER.info("Hello : {}",principal.getName());
        model.addAttribute("userName",principal.getName());
        return "index";
    }
}
